package com.rapido.admin_service.service;

import com.rapido.admin_service.entity.DriverPerformance;
import com.rapido.admin_service.repository.DriverPerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverPerformanceService {

    private final DriverPerformanceRepository driverPerformanceRepository;

    public DriverPerformance saveDriverPerformance(DriverPerformance driverPerformance) {

        double completionRate = calculateCompletionRate(
                driverPerformance.getCompletedRides(),
                driverPerformance.getCancelledRides()
        );

        double score =
                (driverPerformance.getAcceptanceRate() * 0.3)
                        + (completionRate * 0.4)
                        + (driverPerformance.getAverageRating() * 20 * 0.3);

        driverPerformance.setPerformanceScore(score);

        return driverPerformanceRepository.save(driverPerformance);
    }

    public List<DriverPerformance> getTopPerformingDrivers(String region) {

        if (region != null && !region.isEmpty()) {
            return driverPerformanceRepository.findByRegionOrderByPerformanceScoreDesc(region);
        }

        return driverPerformanceRepository.findTop10ByOrderByPerformanceScoreDesc();
    }

    private double calculateCompletionRate(Integer completedRides, Integer cancelledRides) {

        int total = completedRides + cancelledRides;

        if (total == 0) {
            return 0.0;
        }

        return ((double) completedRides / total) * 100;
    }
}