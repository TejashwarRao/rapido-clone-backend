package com.rapido.payment_service.kafka;

import com.rapido.payment_service.entity.ProcessedEvent;
import com.rapido.payment_service.event.PaymentFailedEvent;
import com.rapido.payment_service.event.PaymentSuccessEvent;
import com.rapido.payment_service.event.RideAssignedEvent;
import com.rapido.payment_service.repository.ProcessedEventRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RideAssignedConsumer {

    private final PaymentKafkaProducer producer;

    private final ProcessedEventRepository processedEventRepository;

    @RetryableTopic(
            attempts = "3",
            backoff = @Backoff(
                    delay = 1000,
                    multiplier = 2.0
            )
    )
    @KafkaListener(
            topics = "ride-assigned",
            groupId = "payment-service-group"
    )
    public void consumeRideAssigned(
            RideAssignedEvent event
    ) {

        log.info(
                "Ride Assigned Event Received : {}",
                event
        );

        // IDEMPOTENCY CHECK

        if (processedEventRepository.existsById(
                event.getEventId()
        )) {

            log.warn(
                    "Duplicate Event Ignored : {}",
                    event.getEventId()
            );

            return;
        }

        /*
         * TEST FLAG
         *
         * true  -> payment success
         * false -> payment failure
         */
        boolean paymentSuccess = true;

        if (paymentSuccess) {

            PaymentSuccessEvent successEvent =
                    new PaymentSuccessEvent();

            successEvent.setEventId(
                    UUID.randomUUID().toString()
            );

            successEvent.setRideId(
                    event.getRideId()
            );

            successEvent.setAmount(
                    150.0
            );

            successEvent.setTransactionId(
                    UUID.randomUUID().toString()
            );

            successEvent.setStatus(
                    "SUCCESS"
            );

            producer.publishSuccess(
                    successEvent
            );

            // MARK EVENT AS PROCESSED

            processedEventRepository.save(
                    new ProcessedEvent(
                            event.getEventId()
                    )
            );

            log.info(
                    "Payment Success Event Published : {}",
                    successEvent
            );

        } else {

            PaymentFailedEvent failedEvent =
                    new PaymentFailedEvent();

            failedEvent.setEventId(
                    UUID.randomUUID().toString()
            );

            failedEvent.setRideId(
                    event.getRideId()
            );

            failedEvent.setReason(
                    "Payment Gateway Failure"
            );

            failedEvent.setStatus(
                    "FAILED"
            );

            producer.publishFailure(
                    failedEvent
            );

            processedEventRepository.save(
                    new ProcessedEvent(
                            event.getEventId()
                    )
            );

            log.info(
                    "Payment Failed Event Published : {}",
                    failedEvent
            );
        }
    }
}