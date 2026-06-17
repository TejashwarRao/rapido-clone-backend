package com.rapido.search.recommendation;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    public List<RideRecommendationResponse> getRecommendations(
            Long userId) {

        return List.of(

                RideRecommendationResponse.builder()
                        .pickup("Madhapur")
                        .destination("Airport")
                        .rideType("Bike")
                        .build(),

                RideRecommendationResponse.builder()
                        .pickup("Hitech City")
                        .destination("Gachibowli")
                        .rideType("Auto")
                        .build()
        );
    }
}