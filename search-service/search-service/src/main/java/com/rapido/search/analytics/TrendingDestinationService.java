package com.rapido.search.analytics;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrendingDestinationService {

    public List<TrendingDestinationResponse> getTrending() {

        return List.of(

                TrendingDestinationResponse.builder()
                        .destination("Airport")
                        .searches(150L)
                        .build(),

                TrendingDestinationResponse.builder()
                        .destination("Hitech City")
                        .searches(120L)
                        .build(),

                TrendingDestinationResponse.builder()
                        .destination("Madhapur")
                        .searches(95L)
                        .build()
        );
    }
}