package com.rapido.notification_service.controller;

import com.rapido.notification_service.service.EmailService;

import com.rapido.notification_service.service.OtpService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OtpController {

    private final OtpService otpService;

    private final EmailService emailService;

    public OtpController(
            OtpService otpService,
            EmailService emailService
    ) {

        this.otpService =
                otpService;

        this.emailService =
                emailService;
    }

    // TEST API
    @GetMapping("/test")
    public String test() {

        return "OTP Service Working";
    }

    // SEND OTP
    @PostMapping("/send")
    public String sendOtp(
            @RequestParam String email
    ) {

        String otp =
                otpService.generateOtp(email);

        emailService.sendEmail(
                email,
                "Rapido OTP Verification",
                "Your OTP is: " + otp
        );

        return "OTP Sent Successfully";
    }

    // VERIFY OTP
    @PostMapping("/verify")
    public String verifyOtp(
            @RequestParam String email,
            @RequestParam String otp
    ) {

        boolean valid =
                otpService.verifyOtp(
                        email,
                        otp
                );

        if(valid) {

            return "OTP Verified Successfully";
        }

        return "Invalid OTP";
    }
}