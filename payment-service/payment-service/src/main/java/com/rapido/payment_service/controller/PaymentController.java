package com.rapido.payment_service.controller;

import com.rapido.payment_service.dto.PaymentRequest;
import com.rapido.payment_service.dto.RefundRequest;
import com.rapido.payment_service.dto.TopUpRequest;
import com.rapido.payment_service.service.PaymentService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(
            PaymentService paymentService
    ) {

        this.paymentService = paymentService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createWallet(
            @PathVariable Long userId
    ) {

        return ResponseEntity.ok(
                paymentService.createWallet(userId)
        );
    }

    @PostMapping("/topup")
    public ResponseEntity<?> topUpWallet(
            @Valid
            @RequestBody
            TopUpRequest request
    ) {

        return ResponseEntity.ok(
                paymentService.topUpWallet(
                        request.getUserId(),
                        request.getAmount()
                )
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getWalletBalance(
            @PathVariable Long userId
    ) {

        return ResponseEntity.ok(
                paymentService.getBalance(userId)
        );
    }

    @PostMapping("/ride/pay")
    public ResponseEntity<?> processRidePayment(
            @RequestBody
            PaymentRequest request
    ) {

        return ResponseEntity.ok(
                paymentService.processRidePayment(request)
        );
    }

    @PostMapping("/ride/refund")
    public ResponseEntity<?> refundRide(
            @RequestBody
            RefundRequest request
    ) {

        return ResponseEntity.ok(
                paymentService.refundRide(
                        request.getRideId()
                )
        );
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<?> getTransactionHistory(
            @PathVariable Long userId
    ) {

        return ResponseEntity.ok(
                paymentService.getTransactionHistory(userId)
        );
    }
}