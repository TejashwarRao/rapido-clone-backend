package com.rapido.notification_service.service;

import com.rapido.notification_service.dto.NotificationRequestDTO;
import com.rapido.notification_service.entity.Notification;
import com.rapido.notification_service.entity.NotificationStatus;
import com.rapido.notification_service.repository.NotificationRepository;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final EmailService emailService;

    @Autowired
    private Tracer tracer;

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

        Span span =
                tracer.spanBuilder("notification-sending")
                        .startSpan();

        try {

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

            if (request.getType().name().equals("EMAIL")) {

                Span emailSpan =
                        tracer.spanBuilder("email-delivery")
                                .startSpan();

                try {

                    emailService.sendEmail(
                            request.getRecipient(),
                            request.getTitle(),
                            request.getMessage()
                    );

                } finally {

                    emailSpan.end();
                }
            }

            return notificationRepository.save(
                    notification
            );

        } finally {

            span.end();
        }
    }

    // GET USER NOTIFICATIONS
    public List<Notification> getUserNotifications(
            Long userId
    ) {

        Span span =
                tracer.spanBuilder("notification-history")
                        .startSpan();

        try {

            return notificationRepository.findByUserId(
                    userId
            );

        } finally {

            span.end();
        }
    }
}