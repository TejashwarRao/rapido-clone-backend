package com.rapido.admin_service.service;

import com.rapido.admin_service.dto.MonitoringMetricsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonitoringService {

    private final FraudAlertService fraudAlertService;

    public MonitoringMetricsDTO getMonitoringMetrics() {

        Long fraudAlertsGenerated =
                (long) fraudAlertService.getFraudAlerts(null, null).size();

        return new MonitoringMetricsDTO(
                fraudAlertsGenerated,
                12.5,
                78.3,
                4.8
        );
    }
}