package com.rapido.driver_service.kafka;

import com.rapido.driver_service.event.RideRequestedEvent;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RideRequestedConsumer {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    RideRequestedConsumer.class
            );

    private final DriverKafkaProducerService producerService;

    @KafkaListener(
            topics = "ride-requested",
            groupId = "driver-service-group"
    )
    public void consumeRideRequest(
            RideRequestedEvent event
    ) {

        logger.info(
                "Ride Request Received : {}",
                event
        );

        producerService.publishRideAssignedEvent(
                event.getRideId()
        );
    }
}