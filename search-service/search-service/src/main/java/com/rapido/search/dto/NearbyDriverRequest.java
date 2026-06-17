package com.rapido.search.dto;

import lombok.Data;

@Data
public class NearbyDriverRequest {

    private Double latitude;

    private Double longitude;

    private Double radiusKm;
}