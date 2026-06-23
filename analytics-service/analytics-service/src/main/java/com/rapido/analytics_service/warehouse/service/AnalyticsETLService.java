package com.rapido.analytics_service.warehouse.service;

import com.rapido.analytics_service.dto.events.RideCompletedEvent;
import com.rapido.analytics_service.metrics.AnalyticsMetricsService;
import com.rapido.analytics_service.quality.DataQualityCheck;
import com.rapido.analytics_service.warehouse.entity.DataQualityAudit;
import com.rapido.analytics_service.warehouse.entity.FactRide;
import com.rapido.analytics_service.warehouse.repository.DataQualityAuditRepository;
import com.rapido.analytics_service.warehouse.repository.FactRideRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalyticsETLService {

    private final FactRideRepository factRideRepository;

    private final DataQualityCheck dataQualityCheck;

    private final DataQualityAuditRepository dataQualityAuditRepository;

    private final AnalyticsMetricsService analyticsMetricsService;

    @CacheEvict(
            value = {
                    "dashboard",
                    "revenue",
                    "cities"
            },
            allEntries = true
    )
    public void processRide(
            RideCompletedEvent event
    ) {

        try {

            if (!dataQualityCheck
                    .validateRideEvent(event)) {

                throw new RuntimeException(
                        "Invalid Ride Event"
                );
            }

            if (factRideRepository.existsByRideId(
                    event.getRideId()
            )) {

                throw new RuntimeException(
                        "Duplicate Ride Event"
                );
            }

            FactRide ride =
                    FactRide.builder()
                            .rideId(event.getRideId())
                            .userId(event.getUserId())
                            .driverId(event.getDriverId())
                            .fare(event.getFare())
                            .distance(event.getDistance())
                            .status(event.getStatus())
                            .city(event.getCity())
                            .createdAt(LocalDateTime.now())
                            .build();

            factRideRepository.save(ride);

            analyticsMetricsService.incrementETL();

            log.info(
                    "Analytics ETL processed ride {}",
                    event.getRideId()
            );

        } catch (Exception ex) {

            log.error(
                    "Data quality validation failed",
                    ex
            );

            dataQualityAuditRepository.save(
                    DataQualityAudit.builder()
                            .eventType("RIDE_COMPLETED")
                            .errorMessage(
                                    ex.getMessage()
                            )
                            .createdAt(
                                    LocalDateTime.now()
                            )
                            .build()
            );
        }
    }
}