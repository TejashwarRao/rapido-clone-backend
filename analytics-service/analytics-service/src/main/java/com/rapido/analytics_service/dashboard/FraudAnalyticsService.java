package com.rapido.analytics_service.dashboard;

import com.rapido.analytics_service.metrics.AnalyticsMetricsService;
import com.rapido.analytics_service.warehouse.entity.FraudAlert;
import com.rapido.analytics_service.warehouse.repository.FactRideRepository;
import com.rapido.analytics_service.warehouse.repository.FraudAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FraudAnalyticsService {

    private final FactRideRepository factRideRepository;

    private final FraudAlertRepository fraudAlertRepository;

    private final AnalyticsMetricsService analyticsMetricsService;

    public List<FraudAlert> detectFraud() {

        List<Long> suspiciousDrivers =
                factRideRepository.getSuspiciousDrivers();

        for (Long driverId : suspiciousDrivers) {

            boolean alreadyExists =
                    fraudAlertRepository.existsByEntityIdAndEntityType(
                            driverId,
                            "DRIVER"
                    );

            if (!alreadyExists) {

                FraudAlert alert =
                        FraudAlert.builder()
                                .entityType("DRIVER")
                                .entityId(driverId)
                                .fraudReason(
                                        "High cancellation rate"
                                )
                                .riskLevel("HIGH")
                                .createdAt(
                                        LocalDateTime.now()
                                )
                                .build();

                fraudAlertRepository.save(alert);

                analyticsMetricsService.incrementFraud();
            }
        }

        return fraudAlertRepository.findAll();
    }
}