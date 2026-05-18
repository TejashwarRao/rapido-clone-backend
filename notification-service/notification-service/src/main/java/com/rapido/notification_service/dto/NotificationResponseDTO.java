package com.rapido.notification_service.dto;

import com.rapido.notification_service.entity.NotificationStatus;
import com.rapido.notification_service.entity.NotificationType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponseDTO {

    private Long id;
    private Long userId;
    private NotificationType type;
    private NotificationStatus status;
    private String recipient;
    private String title;
    private String message;
    private Integer retryCount;
    private LocalDateTime createdAt;
}