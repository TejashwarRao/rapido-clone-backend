package com.rapido.driver_service.event;

import lombok.Data;

@Data
public class RideRequestedEvent {

    private String eventId;

    private Long rideId;

    private String userEmail;

    private String pickupLocation;

    private String dropLocation;
}