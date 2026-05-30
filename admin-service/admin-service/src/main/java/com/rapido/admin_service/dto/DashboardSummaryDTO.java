package com.rapido.admin_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardSummaryDTO {

    private Long totalRides;
    private Long activeDrivers;
    private Long activeRiders;
    private Double dailyRevenue;
    private Long failedRides;
    private Long ongoingRides;
}