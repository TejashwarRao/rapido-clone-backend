package com.rapido.notification_service.controller;

import com.rapido.notification_service.dto.NotificationRequestDTO;
import com.rapido.notification_service.dto.NotificationResponseDTO;
import com.rapido.notification_service.dto.OtpRequestDTO;
import com.rapido.notification_service.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<NotificationResponseDTO> sendNotification(
            @Valid @RequestBody NotificationRequestDTO request) {

        return ResponseEntity.ok(notificationService.sendNotification(request));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<NotificationResponseDTO>> getHistory(
            @PathVariable Long userId) {

        return ResponseEntity.ok(notificationService.getHistory(userId));
    }

    @PostMapping("/otp")
    public ResponseEntity<NotificationResponseDTO> sendOtp(
            @Valid @RequestBody OtpRequestDTO request) {

        return ResponseEntity.ok(notificationService.sendOtp(request));
    }
    @PostMapping("/retry/{id}")
    public ResponseEntity<NotificationResponseDTO> retryNotification(
            @PathVariable Long id) {

        return ResponseEntity.ok(notificationService.retryNotification(id));
    }
}