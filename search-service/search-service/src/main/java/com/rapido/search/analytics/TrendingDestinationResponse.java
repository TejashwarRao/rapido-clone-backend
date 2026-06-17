package com.rapido.search.analytics;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrendingDestinationResponse {

    private String destination;

    private Long searches;
}