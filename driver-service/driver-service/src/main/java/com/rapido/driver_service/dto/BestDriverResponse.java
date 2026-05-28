package com.rapido.driver_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BestDriverResponse {

    private Long id;

    private String name;

    private Double rating;

    private Double score;

    private Double latitude;

    private Double longitude;

    private String message;
}
