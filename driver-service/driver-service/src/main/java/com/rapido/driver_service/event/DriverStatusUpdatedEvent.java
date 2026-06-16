package com.rapido.driver_service.event;

import lombok.Data;

@Data
public class DriverStatusUpdatedEvent {

    private String eventId;

    private Long driverId;

    private String status;
}