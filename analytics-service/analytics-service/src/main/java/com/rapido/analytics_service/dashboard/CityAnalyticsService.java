package com.rapido.analytics_service.dashboard;

import com.rapido.analytics_service.dto.CityAnalyticsDto;
import com.rapido.analytics_service.warehouse.repository.FactRideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityAnalyticsService {

    private final FactRideRepository factRideRepository;

    @Cacheable("cities")
    public List<CityAnalyticsDto> getCityAnalytics() {

        return factRideRepository
                .getCityAnalytics()
                .stream()
                .map(row -> new CityAnalyticsDto(
                        (String) row[0],
                        ((Number) row[1]).longValue(),
                        ((Number) row[2]).doubleValue()
                ))
                .toList();
    }
}