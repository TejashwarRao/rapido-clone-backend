package com.rapido.search.kafka;

import com.rapido.search.document.DriverDocument;
import com.rapido.search.event.DriverUpdatedEvent;
import com.rapido.search.repository.DriverSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DriverUpdatedConsumer {

    private final DriverSearchRepository repository;

    @KafkaListener(
            topics = "driver-updated",
            groupId = "search-service"
    )
    public void consume(DriverUpdatedEvent event) {

        log.info("Received Driver Event -> {}", event);

        DriverDocument document =
                DriverDocument.builder()
                        .driverId(event.getDriverId())
                        .name(event.getName())
                        .rating(event.getRating())
                        .vehicleType(event.getVehicleType())
                        .available(event.getAvailable())
                        .location(
                                new GeoPoint(
                                        event.getLatitude(),
                                        event.getLongitude()
                                )
                        )
                        .build();

        repository.save(document);

        log.info(
                "Driver Indexed Successfully -> {}",
                event.getDriverId()
        );
    }
}