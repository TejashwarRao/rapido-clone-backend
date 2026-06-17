package com.rapido.payment_service.event;

import lombok.Data;

@Data
public class PaymentFailedEvent {

    private String eventId;
    private Long rideId;
    private String reason;
    private String status;
}