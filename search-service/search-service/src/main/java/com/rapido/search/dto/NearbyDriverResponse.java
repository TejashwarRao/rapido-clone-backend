package com.rapido.search.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NearbyDriverResponse {

    private Long driverId;
    private String name;
    private Double rating;
    private String vehicleType;
    private Boolean available;
}