package com.rapido.notification_service.event;

import lombok.Data;

@Data
public class NotificationEvent {

    private String eventId;

    private Long userId;

    private String title;

    private String message;
}