package com.rapido.driver_service.kafka;

import com.rapido.driver_service.event.RideAssignedEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DriverKafkaProducerService {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    DriverKafkaProducerService.class
            );

    private final KafkaTemplate<String,Object> kafkaTemplate;

    public DriverKafkaProducerService(
            KafkaTemplate<String,Object> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishRideAssignedEvent(
            Long rideId
    ) {

        RideAssignedEvent event =
                new RideAssignedEvent();

        event.setEventId(
                UUID.randomUUID().toString()
        );

        event.setRideId(
                rideId
        );

        event.setDriverId(
                101L
        );

        event.setStatus(
                "ASSIGNED"
        );

        kafkaTemplate.send(
                "ride-assigned",
                rideId.toString(),
                event
        );

        logger.info(
                "Ride Assigned Event Published : {}",
                event
        );
    }
}