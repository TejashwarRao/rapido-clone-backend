package com.rapido.analytics_service.dto.events;

import lombok.Data;

@Data
public class NotificationSentEvent {

    private Long notificationId;
    private Long userId;
    private String notificationType;
}