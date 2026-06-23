package com.rapido.analytics_service.warehouse.repository;

import com.rapido.analytics_service.warehouse.entity.WeeklyKPI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyKPIRepository
        extends JpaRepository<WeeklyKPI, Long> {
}