package com.rapido.analytics_service.dashboard;

import com.rapido.analytics_service.dto.HeatmapPointDto;
import com.rapido.analytics_service.warehouse.repository.FactRideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeatmapAnalyticsService {

    private final FactRideRepository factRideRepository;

    public List<HeatmapPointDto> getHeatmap() {

        return factRideRepository
                .getPickupHeatmap()
                .stream()
                .map(row -> HeatmapPointDto.builder()
                        .latitude((Double) row[0])
                        .longitude((Double) row[1])
                        .rideCount(((Number) row[2]).longValue())
                        .build())
                .toList();
    }
}