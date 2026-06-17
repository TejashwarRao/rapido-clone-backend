package com.rapido.payment_service.kafka;

import com.rapido.payment_service.event.PaymentFailedEvent;

import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentFailedConsumer {

    @KafkaListener(
            topics = "payment-failed",
            groupId = "ride-service-group"
    )
    public void consumePaymentFailed(
            PaymentFailedEvent event
    ) {

        log.info(
                "Compensation Triggered For Ride : {}",
                event.getRideId()
        );

        log.info(
                "Ride Cancelled"
        );

        log.info(
                "Driver Released"
        );
    }
}