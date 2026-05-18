package com.rapido.ride_service.dto;

import com.rapido.ride_service.entity.RideStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RideResponseDTO {

    private Long rideId;

    private Long userId;

    private Long driverId;

    private RideStatus status;

    private Double estimatedDistance;

    private Double estimatedFare;

    private String message;
}