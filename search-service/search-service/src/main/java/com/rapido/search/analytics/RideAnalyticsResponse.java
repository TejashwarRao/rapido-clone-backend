package com.rapido.search.analytics;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RideAnalyticsResponse {

    private String mostVisitedLocation;

    private String preferredRideType;

    private Integer totalRides;
}