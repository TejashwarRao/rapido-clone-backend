package com.rapido.analytics_service.dashboard;

import com.rapido.analytics_service.aggregation.KPIAggregationService;
import com.rapido.analytics_service.dto.DashboardResponse;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final KPIAggregationService kpiAggregationService;

    @Timed(
            value = "analytics_dashboard_latency"
    )
    @Cacheable("dashboard")
    public DashboardResponse getDashboard() {

        return kpiAggregationService
                .getDashboardMetrics();
    }
}