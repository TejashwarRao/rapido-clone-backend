package com.rapido.payment_service.util;

import java.util.Random;

public class PaymentGatewaySimulator {

    private static final Random random = new Random();

    public static boolean processPayment() {
        return random.nextInt(100) < 90;
    }
}