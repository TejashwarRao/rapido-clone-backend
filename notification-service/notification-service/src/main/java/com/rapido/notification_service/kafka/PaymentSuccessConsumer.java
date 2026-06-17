package com.rapido.notification_service.kafka;

import com.rapido.notification_service.event.PaymentSuccessEvent;

import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentSuccessConsumer {

    @KafkaListener(
            topics = "payment-success",
            groupId = "notification-service-group"
    )
    public void consumePaymentSuccess(
            PaymentSuccessEvent event
    ) {

        log.info(
                "Payment Success Received : {}",
                event
        );

        log.info(
                "Notification Sent For Ride : {}",
                event.getRideId()
        );
    }
}