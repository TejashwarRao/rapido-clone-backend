package com.rapido.analytics_service.warehouse.repository;

import com.rapido.analytics_service.warehouse.entity.CustomerAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAnalyticsRepository
        extends JpaRepository<CustomerAnalytics, Long> {
}