package com.rapido.notification_service.repository;

import com.rapido.notification_service.entity.ProcessedEvent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedEventRepository
        extends JpaRepository<ProcessedEvent, String> {
}