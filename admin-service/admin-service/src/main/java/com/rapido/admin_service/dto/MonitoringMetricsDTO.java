package com.rapido.admin_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonitoringMetricsDTO {

    private Long fraudAlertsGenerated;
    private Double revenueTrend;
    private Double driverUtilization;
    private Double rideCancellationRatio;
}