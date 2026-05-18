package com.rapido.ride_service.event;

import com.rapido.ride_service.entity.RideStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RideStatusEvent {

    private Long rideId;

    private RideStatus status;

    private String message;

    private LocalDateTime timestamp;
}