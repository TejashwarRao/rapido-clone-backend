package com.rapido.admin_service.service;

import com.rapido.admin_service.dto.RevenueAnalyticsDTO;
import org.springframework.stereotype.Service;

@Service
public class RevenueAnalyticsService {

    public RevenueAnalyticsDTO getRevenueAnalytics() {

        return new RevenueAnalyticsDTO(
                15000.0,     // daily revenue
                85000.0,     // weekly revenue
                350000.0,    // monthly revenue
                220000.0,    // driver payouts
                130000.0     // platform commission
        );
    }
}