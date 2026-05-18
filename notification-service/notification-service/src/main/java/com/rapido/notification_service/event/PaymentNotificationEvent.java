package com.rapido.notification_service.event;

import lombok.Data;

@Data
public class PaymentNotificationEvent {

    private Long userId;
    private String recipient;
    private Double amount;
    private String paymentStatus;
}