package com.rapido.notification_service.event;

import lombok.Data;

@Data
public class RideNotificationEvent {

    private Long userId;
    private String recipient;
    private String rideStatus;
    private String pickupLocation;
    private String dropLocation;
    private String driverName;
}