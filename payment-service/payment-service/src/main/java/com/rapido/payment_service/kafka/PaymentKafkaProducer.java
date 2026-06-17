package com.rapido.payment_service.kafka;

import com.rapido.payment_service.event.PaymentFailedEvent;
import com.rapido.payment_service.event.PaymentSuccessEvent;

import lombok.RequiredArgsConstructor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishSuccess(
            PaymentSuccessEvent event
    ) {

        kafkaTemplate.send(
                "payment-success",
                event.getRideId().toString(),
                event
        );
    }

    public void publishFailure(
            PaymentFailedEvent event
    ) {

        kafkaTemplate.send(
                "payment-failed",
                event.getRideId().toString(),
                event
        );
    }
}