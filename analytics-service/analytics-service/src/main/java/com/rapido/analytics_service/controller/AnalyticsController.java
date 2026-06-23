package com.rapido.analytics_service.controller;

import com.rapido.analytics_service.dashboard.*;
import com.rapido.analytics_service.dto.*;
import com.rapido.analytics_service.warehouse.entity.*;
import com.rapido.analytics_service.warehouse.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final DashboardService dashboardService;
    private final CityAnalyticsService cityAnalyticsService;
    private final RevenueAnalyticsService revenueAnalyticsService;
    private final DriverAnalyticsService driverAnalyticsService;
    private final FraudAnalyticsService fraudAnalyticsService;

    private final DailyKPIRepository dailyKPIRepository;
    private final WeeklyKPIRepository weeklyKPIRepository;
    private final MonthlyKPIRepository monthlyKPIRepository;
    private final AnalyticsJobAuditRepository analyticsJobAuditRepository;
    private final DataQualityAuditRepository dataQualityAuditRepository;

    @GetMapping("/dashboard")
    public DashboardResponse dashboard() {
        return dashboardService.getDashboard();
    }

    @GetMapping("/cities")
    public List<CityAnalyticsDto> cities() {
        return cityAnalyticsService.getCityAnalytics();
    }

    @GetMapping("/revenue")
    public RevenueResponse revenue() {
        return revenueAnalyticsService.getRevenue();
    }

    @GetMapping("/daily-kpis")
    public List<DailyKPI> dailyKPIs() {
        return dailyKPIRepository.findAll();
    }

    @GetMapping("/weekly-kpis")
    public List<WeeklyKPI> weeklyKPIs() {
        return weeklyKPIRepository.findAll();
    }

    @GetMapping("/monthly-kpis")
    public List<MonthlyKPI> monthlyKPIs() {
        return monthlyKPIRepository.findAll();
    }

    @GetMapping("/audit")
    public List<AnalyticsJobAudit> auditLogs() {
        return analyticsJobAuditRepository.findAll();
    }

    @GetMapping("/quality")
    public List<DataQualityAudit> qualityLogs() {
        return dataQualityAuditRepository.findAll();
    }

    @GetMapping("/drivers/{driverId}")
    public DriverAnalytics driverAnalytics(
            @PathVariable Long driverId
    ) {
        return driverAnalyticsService
                .generateDriverAnalytics(driverId);
    }

    @GetMapping("/fraud")
    public List<FraudAlert> fraud() {
        return fraudAnalyticsService.detectFraud();
    }
}