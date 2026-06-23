package com.rapido.analytics_service.warehouse.repository;

import com.rapido.analytics_service.warehouse.entity.DailyKPI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyKPIRepository
        extends JpaRepository<DailyKPI, Long> {
}