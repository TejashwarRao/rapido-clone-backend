package com.rapido.payment_service.event;

import lombok.Data;

@Data
public class PaymentSuccessEvent {

    private String eventId;

    private Long rideId;

    private Double amount;

    private String transactionId;

    private String status;
}