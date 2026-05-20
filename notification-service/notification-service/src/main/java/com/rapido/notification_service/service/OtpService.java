package com.rapido.notification_service.service;

import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;

import java.util.Random;

import java.util.concurrent.TimeUnit;

@Service
public class OtpService {

    private final RedisTemplate<String, Object> redisTemplate;

    public OtpService(
            RedisTemplate<String, Object> redisTemplate
    ) {

        this.redisTemplate =
                redisTemplate;
    }

    // GENERATE OTP
    public String generateOtp(
            String email
    ) {

        Random random =
                new Random();

        int otp =
                100000 + random.nextInt(900000);

        String otpString =
                String.valueOf(otp);

        // STORE OTP IN REDIS (5 MINUTES)

        redisTemplate.opsForValue().set(
                email,
                otpString,
                5,
                TimeUnit.MINUTES
        );

        return otpString;
    }

    // VERIFY OTP
    public boolean verifyOtp(
            String email,
            String otp
    ) {

        Object storedOtp =
                redisTemplate.opsForValue().get(email);

        if(storedOtp == null) {

            return false;
        }

        return storedOtp.toString().equals(otp);
    }
}