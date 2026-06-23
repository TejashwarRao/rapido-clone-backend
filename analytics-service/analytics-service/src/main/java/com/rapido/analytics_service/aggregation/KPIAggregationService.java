package com.rapido.analytics_service.aggregation;

import com.rapido.analytics_service.dto.DashboardResponse;
import com.rapido.analytics_service.warehouse.entity.FactRide;
import com.rapido.analytics_service.warehouse.repository.FactRideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KPIAggregationService {

    private final FactRideRepository factRideRepository;

    public DashboardResponse getDashboardMetrics() {

        Long totalRides =
                factRideRepository.count();

        Long completedRides =
                factRideRepository.countByStatus(
                        "COMPLETED"
                );

        Long cancelledRides =
                factRideRepository.countByStatus(
                        "CANCELLED"
                );

        Double revenue =
                factRideRepository.findAll()
                        .stream()
                        .mapToDouble(FactRide::getFare)
                        .sum();

        return DashboardResponse.builder()
                .totalRides(totalRides)
                .completedRides(completedRides)
                .cancelledRides(cancelledRides)
                .totalRevenue(revenue)
                .build();
    }
}