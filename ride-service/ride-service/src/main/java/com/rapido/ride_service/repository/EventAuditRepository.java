package com.rapido.ride_service.repository;

import com.rapido.ride_service.entity.EventAudit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventAuditRepository
        extends JpaRepository<EventAudit, Long> {

    Optional<EventAudit> findByEventId(
            String eventId
    );
}