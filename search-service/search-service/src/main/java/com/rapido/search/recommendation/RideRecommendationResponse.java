package com.rapido.search.recommendation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RideRecommendationResponse {

    private String pickup;

    private String destination;

    private String rideType;
}