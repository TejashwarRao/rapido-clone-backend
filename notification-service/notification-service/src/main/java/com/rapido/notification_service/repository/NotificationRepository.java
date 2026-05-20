package com.rapido.notification_service.repository;

import com.rapido.notification_service.entity.Notification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    // GET USER NOTIFICATIONS
    List<Notification> findByUserId(
            Long userId
    );

    // DELETE OLD NOTIFICATIONS
    void deleteByCreatedAtBefore(
            LocalDateTime dateTime
    );
}