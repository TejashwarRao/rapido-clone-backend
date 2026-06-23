package com.rapido.analytics_service.dto.events;

import lombok.Data;

@Data
public class RideCompletedEvent {

    private Long rideId;
    private Long userId;
    private Long driverId;
    private Double fare;
    private Double distance;
    private String status;
    private String city;
}