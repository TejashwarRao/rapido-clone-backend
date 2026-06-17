package com.rapido.search.controller;

import com.rapido.search.analytics.SearchEvent;
import com.rapido.search.analytics.SearchEventPublisher;
import com.rapido.search.document.DriverDocument;
import com.rapido.search.event.DriverUpdatedEvent;
import com.rapido.search.metrics.SearchMetricsService;
import com.rapido.search.repository.DriverSearchRepository;
import com.rapido.search.service.GeoSearchService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
@Validated
public class SearchController {

    private final DriverSearchRepository repository;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final GeoSearchService geoSearchService;

    private final SearchEventPublisher searchEventPublisher;

    private final SearchMetricsService metricsService;

    @GetMapping("/health")
    public String health() {

        return "Search Service Running";
    }

    @PostMapping("/driver")
    public DriverDocument saveDriver(
            @RequestBody DriverDocument driver) {

        return repository.save(driver);
    }

    @GetMapping("/drivers")
    public Iterable<DriverDocument> getDrivers() {

        return repository.findAll();
    }

    @GetMapping("/drivers/nearby")
    public List<DriverDocument> nearbyDrivers(

            @RequestParam
            @NotNull(message = "Latitude is required")
            Double latitude,

            @RequestParam
            @NotNull(message = "Longitude is required")
            Double longitude,

            @RequestParam
            @NotNull(message = "Radius is required")
            @Min(value = 1, message = "Radius must be >= 1 KM")
            @Max(value = 50, message = "Radius must be <= 50 KM")
            Double radiusKm) {

        // Metrics
        metricsService.incrementSearches();

        long startTime = System.currentTimeMillis();

        List<DriverDocument> drivers =
                geoSearchService.nearbyDrivers(
                        latitude,
                        longitude,
                        radiusKm
                );

        long latency =
                System.currentTimeMillis() - startTime;

        // Analytics Event
        searchEventPublisher.publish(
                SearchEvent.builder()
                        .searchTerm("nearby-driver-search")
                        .userId(1L)
                        .responseTime(latency)
                        .selectedResult(
                                drivers.isEmpty()
                                        ? "NO_RESULTS"
                                        : drivers.get(0).getName()
                        )
                        .build()
        );

        return drivers;
    }

    @PostMapping("/test-event")
    public String sendTestEvent(
            @RequestBody DriverUpdatedEvent event) {

        kafkaTemplate.send(
                "driver-updated",
                event
        );

        return "Driver Event Published";
    }
}