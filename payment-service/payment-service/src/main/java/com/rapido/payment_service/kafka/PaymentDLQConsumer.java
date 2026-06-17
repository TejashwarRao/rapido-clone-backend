package com.rapido.payment_service.kafka;

import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentDLQConsumer {

    @KafkaListener(
            topics = "payment-dlq",
            groupId = "payment-dlq-group"
    )
    public void consumeDLQ(
            String message
    ) {

        log.error(
                "DLQ Message Received : {}",
                message
        );
    }
}