package com.rapido.admin_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RevenueAnalyticsDTO {

    private Double dailyRevenue;
    private Double weeklyRevenue;
    private Double monthlyRevenue;
    private Double driverPayouts;
    private Double platformCommission;
}