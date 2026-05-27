package com.rapido.driver_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurgePricingResponse {

    private Double baseFare;

    private Double surgeMultiplier;

    private Double finalFare;

    private Boolean peakHour;

    private Boolean highDemand;

    private Boolean trafficApplied;

    private String message;
}