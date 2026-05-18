package com.rapido.notification_service.service;

import com.rapido.notification_service.dto.NotificationRequestDTO;
import com.rapido.notification_service.dto.NotificationResponseDTO;
import com.rapido.notification_service.dto.OtpRequestDTO;
import com.rapido.notification_service.entity.Notification;
import com.rapido.notification_service.entity.NotificationStatus;
import com.rapido.notification_service.entity.NotificationType;
import com.rapido.notification_service.repository.NotificationRepository;
import com.rapido.notification_service.websocket.WebSocketNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;
    private final SmsService smsService;
    private final WebSocketNotificationService webSocketNotificationService;

    public NotificationResponseDTO sendNotification(NotificationRequestDTO request) {

        Notification notification = Notification.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .recipient(request.getRecipient())
                .title(request.getTitle())
                .message(request.getMessage())
                .status(NotificationStatus.PENDING)
                .retryCount(0)
                .createdAt(LocalDateTime.now())
                .build();

        processNotification(notification);

        Notification saved = notificationRepository.save(notification);

        NotificationResponseDTO response = mapToResponse(saved);

        webSocketNotificationService.sendToUser(saved.getUserId(), response);

        return response;
    }

    public NotificationResponseDTO sendOtp(OtpRequestDTO request) {

        String otp = String.valueOf(new Random().nextInt(900000) + 100000);

        NotificationRequestDTO notificationRequest = new NotificationRequestDTO();
        notificationRequest.setUserId(request.getUserId());
        notificationRequest.setRecipient(request.getRecipient());
        notificationRequest.setTitle("OTP Verification");
        notificationRequest.setMessage("Your OTP is: " + otp + ". It is valid for 5 minutes.");

        if (request.getRecipient().contains("@")) {
            notificationRequest.setType(NotificationType.EMAIL);
        } else {
            notificationRequest.setType(NotificationType.SMS);
        }

        return sendNotification(notificationRequest);
    }

    public NotificationResponseDTO retryNotification(Long id) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        if (notification.getRetryCount() >= 3) {
            throw new RuntimeException("Max retry limit reached");
        }

        notification.setStatus(NotificationStatus.RETRYING);
        notification.setRetryCount(notification.getRetryCount() + 1);

        processNotification(notification);

        Notification saved = notificationRepository.save(notification);

        NotificationResponseDTO response = mapToResponse(saved);

        webSocketNotificationService.sendToUser(saved.getUserId(), response);

        return response;
    }

    private void processNotification(Notification notification) {
        try {
            if (notification.getType() == NotificationType.EMAIL) {
                emailService.sendEmail(
                        notification.getRecipient(),
                        notification.getTitle(),
                        notification.getMessage()
                );
            } else if (notification.getType() == NotificationType.SMS) {
                smsService.sendSms(
                        notification.getRecipient(),
                        notification.getMessage()
                );
            } else if (notification.getType() == NotificationType.PUSH) {
                System.out.println("Push notification simulated: " + notification.getMessage());
            }

            notification.setStatus(NotificationStatus.SENT);

        } catch (Exception e) {
            notification.setStatus(NotificationStatus.FAILED);
        }
    }

    public List<NotificationResponseDTO> getHistory(Long userId) {
        return notificationRepository.findByUserId(userId, PageRequest.of(0, 20))
                .getContent()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private NotificationResponseDTO mapToResponse(Notification notification) {
        return NotificationResponseDTO.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .type(notification.getType())
                .status(notification.getStatus())
                .recipient(notification.getRecipient())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .retryCount(notification.getRetryCount())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}