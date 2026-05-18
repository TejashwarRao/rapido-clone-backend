package com.rapido.notification_service.dto;

import com.rapido.notification_service.entity.NotificationType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationEvent {

    @NotNull
    private Long userId;

    @NotNull
    private NotificationType type;

    @NotBlank
    private String recipient;

    @NotBlank
    private String title;

    @NotBlank
    private String message;
}