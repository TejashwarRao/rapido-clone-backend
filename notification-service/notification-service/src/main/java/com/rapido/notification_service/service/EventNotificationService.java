package com.rapido.notification_service.service;

import com.rapido.notification_service.dto.NotificationRequestDTO;
import com.rapido.notification_service.entity.NotificationType;
import com.rapido.notification_service.event.PaymentNotificationEvent;
import com.rapido.notification_service.event.RideNotificationEvent;
import com.rapido.notification_service.template.NotificationTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventNotificationService {

    private final NotificationService notificationService;
    private final NotificationTemplateService templateService;

    @Async
    public void processRideEvent(RideNotificationEvent event) {

        NotificationRequestDTO request = new NotificationRequestDTO();

        request.setUserId(event.getUserId());
        request.setRecipient(event.getRecipient());
        request.setType(NotificationType.SMS);
        request.setTitle("Ride Update");

        if ("RIDE_CONFIRMED".equalsIgnoreCase(event.getRideStatus())) {

            request.setMessage(
                    templateService.rideConfirmation(
                            event.getPickupLocation(),
                            event.getDropLocation()
                    )
            );

        } else if ("DRIVER_ARRIVED".equalsIgnoreCase(event.getRideStatus())) {

            request.setMessage(
                    templateService.driverArrival(
                            event.getDriverName()
                    )
            );

        } else {

            request.setMessage(
                    "Your ride status is: " + event.getRideStatus()
            );
        }

        notificationService.sendNotification(request);
    }

    @Async
    public void processPaymentEvent(PaymentNotificationEvent event) {

        NotificationRequestDTO request = new NotificationRequestDTO();

        request.setUserId(event.getUserId());
        request.setRecipient(event.getRecipient());
        request.setType(NotificationType.EMAIL);
        request.setTitle("Payment Update");

        if ("SUCCESS".equalsIgnoreCase(event.getPaymentStatus())) {

            request.setMessage(
                    templateService.paymentSuccess(
                            event.getAmount()
                    )
            );

        } else {

            request.setMessage(
                    "Payment status: " + event.getPaymentStatus()
            );
        }

        notificationService.sendNotification(request);
    }
}