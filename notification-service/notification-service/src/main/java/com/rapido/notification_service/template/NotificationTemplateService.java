package com.rapido.notification_service.template;

import org.springframework.stereotype.Service;

@Service
public class NotificationTemplateService {

    public String rideConfirmation(String pickup, String drop) {
        return "Your ride is confirmed from " + pickup + " to " + drop + ".";
    }

    public String paymentSuccess(Double amount) {
        return "Payment successful. Amount paid: ₹" + amount + ".";
    }

    public String otpVerification(String otp) {
        return "Your OTP is: " + otp + ". It is valid for 5 minutes.";
    }

    public String driverArrival(String driverName) {
        return "Your driver " + driverName + " has arrived at your pickup location.";
    }
}