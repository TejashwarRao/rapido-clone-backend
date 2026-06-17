package com.rapido.search.analytics;

import org.springframework.stereotype.Service;

@Service
public class RideAnalyticsService {

    public RideAnalyticsResponse analyze(Long userId) {

        return RideAnalyticsResponse.builder()
                .mostVisitedLocation("Airport")
                .preferredRideType("Bike")
                .totalRides(25)
                .build();
    }
}