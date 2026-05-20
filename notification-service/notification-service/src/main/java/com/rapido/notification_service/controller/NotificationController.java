package com.rapido.notification_service.controller;

import com.rapido.notification_service.dto.NotificationRequestDTO;

import com.rapido.notification_service.entity.Notification;

import com.rapido.notification_service.service.NotificationService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(
            NotificationService notificationService
    ) {

        this.notificationService =
                notificationService;
    }

    // TEST API
    @GetMapping("/test")
    public String test() {

        return "Notification Service Working";
    }

    // SEND NOTIFICATION
    @PostMapping
    public Notification sendNotification(
            @RequestBody NotificationRequestDTO request
    ) {

        return notificationService.sendNotification(
                request
        );
    }

    // GET USER NOTIFICATIONS
    @GetMapping("/{userId}")
    public List<Notification> getUserNotifications(
            @PathVariable Long userId
    ) {

        return notificationService.getUserNotifications(
                userId
        );
    }
}