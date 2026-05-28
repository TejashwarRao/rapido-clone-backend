package com.rapido.driver_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NearbyDriverResponse {

    private Long id;

    private String name;

    private Double latitude;

    private Double longitude;

    private Double rating;

    private Double score;
}