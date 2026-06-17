package com.rapido.ride_service.event;

import lombok.Data;

@Data
public class PaymentSuccessEvent {

    private String eventId;

    private Long rideId;

    private String transactionId;

    private Double amount;
}