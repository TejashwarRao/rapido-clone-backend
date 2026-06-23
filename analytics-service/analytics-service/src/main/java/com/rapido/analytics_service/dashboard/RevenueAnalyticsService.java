package com.rapido.analytics_service.dashboard;

import com.rapido.analytics_service.dto.RevenueResponse;
import com.rapido.analytics_service.warehouse.repository.FactRideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RevenueAnalyticsService {

    private final FactRideRepository factRideRepository;

    @Cacheable("revenue")
    public RevenueResponse getRevenue() {

        Double revenue = factRideRepository.getTotalRevenue();

        return new RevenueResponse(revenue);
    }
}