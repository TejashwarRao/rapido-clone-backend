package com.rapido.ride_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.rapido.ride_service.entity.EventAudit;
import com.rapido.ride_service.repository.EventAuditRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventAuditService {

    private final EventAuditRepository repository;
    private final ObjectMapper objectMapper;

    public EventAuditService(
            EventAuditRepository repository,
            ObjectMapper objectMapper
    ) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public void saveEvent(
            String eventId,
            String eventType,
            String topicName,
            Object payload
    ) {

        try {

            EventAudit audit =
                    new EventAudit();

            audit.setEventId(
                    eventId
            );

            audit.setEventType(
                    eventType
            );

            audit.setTopicName(
                    topicName
            );

            audit.setPayload(
                    objectMapper.writeValueAsString(
                            payload
                    )
            );

            audit.setCreatedAt(
                    LocalDateTime.now()
            );

            repository.save(
                    audit
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed To Save Audit Event",
                    e
            );
        }
    }
}