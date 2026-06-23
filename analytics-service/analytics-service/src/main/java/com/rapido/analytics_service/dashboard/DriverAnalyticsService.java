package com.rapido.analytics_service.dashboard;

import com.rapido.analytics_service.warehouse.entity.DriverAnalytics;
import com.rapido.analytics_service.warehouse.repository.DriverAnalyticsRepository;
import com.rapido.analytics_service.warehouse.repository.FactRideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DriverAnalyticsService {

    private final FactRideRepository factRideRepository;
    private final DriverAnalyticsRepository driverAnalyticsRepository;

    public DriverAnalytics generateDriverAnalytics(
            Long driverId
    ) {

        Long total =
                factRideRepository
                        .getTotalRidesByDriver(driverId);

        Long completed =
                factRideRepository
                        .getCompletedRidesByDriver(driverId);

        Long cancelled =
                factRideRepository
                        .getCancelledRidesByDriver(driverId);

        double completionRate =
                total == 0 ? 0 :
                        (completed * 100.0) / total;

        double cancellationRate =
                total == 0 ? 0 :
                        (cancelled * 100.0) / total;

        DriverAnalytics analytics =
                DriverAnalytics.builder()
                        .driverId(driverId)
                        .totalRides(total)
                        .completedRides(completed)
                        .cancelledRides(cancelled)
                        .acceptanceRate(95.0)
                        .completionRate(completionRate)
                        .cancellationRate(cancellationRate)
                        .averageRating(4.8)
                        .createdAt(LocalDateTime.now())
                        .build();

        return driverAnalyticsRepository.save(analytics);
    }
}