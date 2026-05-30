package com.rapido.admin_service.repository;

import com.rapido.admin_service.entity.DriverPerformance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverPerformanceRepository extends JpaRepository<DriverPerformance, Long> {

    List<DriverPerformance> findTop10ByOrderByPerformanceScoreDesc();

    List<DriverPerformance> findByRegionOrderByPerformanceScoreDesc(String region);
}