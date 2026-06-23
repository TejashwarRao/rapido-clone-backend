package com.rapido.analytics_service.warehouse.repository;

import com.rapido.analytics_service.warehouse.entity.DriverAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverAnalyticsRepository
        extends JpaRepository<DriverAnalytics, Long> {
}