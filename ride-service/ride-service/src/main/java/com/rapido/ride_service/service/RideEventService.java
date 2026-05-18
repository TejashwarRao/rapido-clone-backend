package com.rapido.ride_service.service;

import com.rapido.ride_service.entity.RideStatus;
import com.rapido.ride_service.event.RideStatusEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RideEventService {

    private final SimpMessagingTemplate messagingTemplate;

    public RideEventService(
            SimpMessagingTemplate messagingTemplate
    ) {

        this.messagingTemplate = messagingTemplate;
    }

    public void publishRideStatus(
            Long rideId,
            RideStatus status,
            String message
    ) {

        RideStatusEvent event =
                new RideStatusEvent();

        event.setRideId(rideId);

        event.setStatus(status);

        event.setMessage(message);

        messagingTemplate.convertAndSend(
                "/topic/ride-status/" + rideId,
                event
        );

        log.info(
                "Ride status event pushed for ride {}",
                rideId
        );
    }
}