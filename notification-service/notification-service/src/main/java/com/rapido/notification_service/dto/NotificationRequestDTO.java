package com.rapido.notification_service.dto;

import com.rapido.notification_service.entity.NotificationType;

public class NotificationRequestDTO {

    private Long userId;

    private String title;

    private String message;

    private String recipient;

    private NotificationType type;

    // GETTERS

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipient() {
        return recipient;
    }

    public NotificationType getType() {
        return type;
    }

    // SETTERS

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
}