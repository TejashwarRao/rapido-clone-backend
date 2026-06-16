package com.rapido.payment_service.event;

import lombok.Data;

@Data
public class PaymentInitiatedEvent {

    private String eventId;

    private Long rideId;

    private Long payerId;

    private Long driverId;

    private Double amount;
}