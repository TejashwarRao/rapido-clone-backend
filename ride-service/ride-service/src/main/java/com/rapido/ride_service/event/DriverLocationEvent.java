package com.rapido.ride_service.event;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DriverLocationEvent {

    private Long rideId;

    private Long driverId;

    private Double latitude;

    private Double longitude;

    private Integer etaMinutes;

    private LocalDateTime timestamp;
}