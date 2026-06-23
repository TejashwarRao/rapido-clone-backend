package com.rapido.analytics_service.warehouse.repository;

import com.rapido.analytics_service.warehouse.entity.MonthlyKPI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyKPIRepository
        extends JpaRepository<MonthlyKPI, Long> {
}