package com.rapido.ride_service.kafka;

import com.rapido.ride_service.event.RideRequestedEvent;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    KafkaProducerService.class
            );

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishRideRequestedEvent(
            RideRequestedEvent event
    ) {

        kafkaTemplate.send(
                "ride-requested",
                event.getRideId().toString(),
                event
        ).whenComplete((result, ex) -> {

            if (ex == null) {

                logger.info(
                        "RideRequestedEvent Published : {}",
                        event
                );

            } else {

                logger.error(
                        "Failed To Publish Event",
                        ex
                );
            }
        });
    }
}