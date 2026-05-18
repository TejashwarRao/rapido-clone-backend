package com.rapido.notification_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class SmsService {

    private final Random random = new Random();

    public void sendSms(String phoneNumber, String message) {

        boolean success = random.nextBoolean();

        if (!success) {
            throw new RuntimeException("SMS delivery failed");
        }

        log.info("SMS sent successfully to {} : {}", phoneNumber, message);
    }
}