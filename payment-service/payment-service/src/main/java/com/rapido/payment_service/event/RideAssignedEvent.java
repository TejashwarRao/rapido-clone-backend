package com.rapido.payment_service.event;

import lombok.Data;

@Data
public class RideAssignedEvent {

    private String eventId;

    private Long rideId;

    private Long driverId;

    private String status;
}