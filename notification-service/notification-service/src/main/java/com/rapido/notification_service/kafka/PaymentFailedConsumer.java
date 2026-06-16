package com.rapido.notification_service.kafka;

import com.rapido.notification_service.event.PaymentFailedEvent;

import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentFailedConsumer {

    @KafkaListener(
            topics = "payment-failed",
            groupId = "notification-service-group"
    )
    public void consumePaymentFailed(
            PaymentFailedEvent event
    ) {

        log.info(
                "Payment Failed Received : {}",
                event
        );

        log.info(
                "Failure Notification Sent For Ride : {}",
                event.getRideId()
        );
    }
}