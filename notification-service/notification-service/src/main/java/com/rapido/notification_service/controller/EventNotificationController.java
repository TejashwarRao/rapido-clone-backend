package com.rapido.notification_service.controller;

import com.rapido.notification_service.event.PaymentNotificationEvent;
import com.rapido.notification_service.event.RideNotificationEvent;
import com.rapido.notification_service.service.EventNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications/events")
@RequiredArgsConstructor
public class EventNotificationController {

    private final EventNotificationService eventNotificationService;

    @PostMapping("/ride")
    public ResponseEntity<String> handleRideEvent(
            @RequestBody RideNotificationEvent event) {

        eventNotificationService.processRideEvent(event);
        return ResponseEntity.ok("Ride notification event processed successfully");
    }

    @PostMapping("/payment")
    public ResponseEntity<String> handlePaymentEvent(
            @RequestBody PaymentNotificationEvent event) {

        eventNotificationService.processPaymentEvent(event);
        return ResponseEntity.ok("Payment notification event processed successfully");
    }
}