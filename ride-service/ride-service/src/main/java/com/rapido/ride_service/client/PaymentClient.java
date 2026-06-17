package com.rapido.ride_service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentClient {

    private final RestTemplate restTemplate;

    public String callPaymentService() {

        return restTemplate.getForObject(
                "http://localhost:8084/payments/test",
                String.class
        );
    }
}