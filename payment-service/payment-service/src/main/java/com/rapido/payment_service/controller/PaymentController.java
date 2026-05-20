package com.rapido.payment_service.controller;

import com.rapido.payment_service.dto.PaymentRequest;
import com.rapido.payment_service.dto.RefundRequest;
import com.rapido.payment_service.dto.TopUpRequest;
import com.rapido.payment_service.entity.Transaction;
import com.rapido.payment_service.entity.Wallet;
import com.rapido.payment_service.service.PaymentService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(
            PaymentService paymentService
    ) {
        this.paymentService = paymentService;
    }

    @GetMapping("/test")
    public String test() {
        return "Payment Service Working";
    }

    @PostMapping("/wallet/{userId}")
    public Wallet createWallet(
            @PathVariable Long userId
    ) {
        return paymentService.createWallet(userId);
    }

    @PostMapping("/wallet/topup")
    public Wallet topUpWallet(
            @RequestBody TopUpRequest request
    ) {
        return paymentService.topUpWallet(
                request.getUserId(),
                request.getAmount()
        );
    }

    @GetMapping("/wallet/balance/{userId}")
    public Double getBalance(
            @PathVariable Long userId
    ) {
        return paymentService.getBalance(userId);
    }

    @PostMapping("/ride/pay")
    public Transaction processRidePayment(
            @RequestBody PaymentRequest request
    ) {
        return paymentService.processRidePayment(request);
    }

    @PostMapping("/refund")
    public String refundRide(
            @RequestBody RefundRequest request
    ) {
        return paymentService.refundRide(
                request.getRideId()
        );
    }

    @GetMapping("/history/{userId}")
    public List<Transaction> getHistory(
            @PathVariable Long userId
    ) {
        return paymentService.getTransactionHistory(userId);
    }
}