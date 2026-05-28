package com.rapido.driver_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EtaResponse {

    private Double distanceKm;

    private Double averageSpeed;

    private Integer estimatedMinutes;

    private Boolean trafficApplied;

    private Boolean peakHourApplied;

    private String message;
}
