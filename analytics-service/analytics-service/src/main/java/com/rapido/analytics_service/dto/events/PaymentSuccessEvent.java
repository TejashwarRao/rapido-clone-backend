package com.rapido.analytics_service.dto.events;

import lombok.Data;

@Data
public class PaymentSuccessEvent {

    private Long paymentId;
    private Long rideId;
    private Double amount;
    private String paymentStatus;
}