package com.rapido.ride_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.rapido.ride_service.entity.EventAudit;
import com.rapido.ride_service.event.RideRequestedEvent;
import com.rapido.ride_service.kafka.KafkaProducerService;
import com.rapido.ride_service.repository.EventAuditRepository;

import org.springframework.stereotype.Service;

@Service
public class EventReplayService {

    private final EventAuditRepository repository;

    private final KafkaProducerService
            kafkaProducerService;

    private final ObjectMapper objectMapper;

    public EventReplayService(
            EventAuditRepository repository,
            KafkaProducerService kafkaProducerService,
            ObjectMapper objectMapper
    ) {

        this.repository =
                repository;

        this.kafkaProducerService =
                kafkaProducerService;

        this.objectMapper =
                objectMapper;
    }

    public String replayEvent(
            String eventId
    ) {

        EventAudit audit =
                repository.findByEventId(
                                eventId
                        )
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Event Not Found"
                                        )
                        );

        try {

            if (
                    "RideRequestedEvent"
                            .equals(
                                    audit.getEventType()
                            )
            ) {

                RideRequestedEvent event =
                        objectMapper.readValue(
                                audit.getPayload(),
                                RideRequestedEvent.class
                        );

                kafkaProducerService
                        .publishRideRequestedEvent(
                                event
                        );

                return "Event Replayed Successfully";
            }

            return "Replay Not Supported";

        } catch (Exception e) {

            throw new RuntimeException(
                    e
            );
        }
    }
}