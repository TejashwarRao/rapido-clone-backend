package com.rapido.notification_service.service;

import com.rapido.notification_service.dto.NotificationRequestDTO;

import com.rapido.notification_service.entity.Notification;

import com.rapido.notification_service.entity.NotificationStatus;

import com.rapido.notification_service.repository.NotificationRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final EmailService emailService;

    public NotificationService(
            NotificationRepository notificationRepository,
            EmailService emailService
    ) {

        this.notificationRepository =
                notificationRepository;

        this.emailService =
                emailService;
    }

    // SEND NOTIFICATION
    public Notification sendNotification(
            NotificationRequestDTO request
    ) {

        Notification notification =
                new Notification();

        notification.setUserId(
                request.getUserId()
        );

        notification.setTitle(
                request.getTitle()
        );

        notification.setMessage(
                request.getMessage()
        );

        notification.setRecipient(
                request.getRecipient()
        );

        notification.setType(
                request.getType()
        );

        notification.setStatus(
                NotificationStatus.SENT
        );

        notification.setCreatedAt(
                LocalDateTime.now()
        );

        // SEND REAL EMAIL

        if(request.getType().name().equals("EMAIL")) {

            emailService.sendEmail(
                    request.getRecipient(),
                    request.getTitle(),
                    request.getMessage()
            );
        }

        return notificationRepository.save(
                notification
        );
    }

    // GET USER NOTIFICATIONS
    public List<Notification> getUserNotifications(
            Long userId
    ) {

        return notificationRepository.findByUserId(
                userId
        );
    }
}