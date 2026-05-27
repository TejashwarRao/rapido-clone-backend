package com.rapido.driver_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RideReassignmentResponse {

    private Long previousDriverId;

    private Long newDriverId;

    private String previousDriverName;

    private String newDriverName;

    private String message;
}