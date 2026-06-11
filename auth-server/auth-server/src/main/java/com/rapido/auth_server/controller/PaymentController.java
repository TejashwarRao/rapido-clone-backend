package com.rapido.auth_server.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @GetMapping("/{id}")
    @PreAuthorize(
            "@abacService.canAccessPayment(#id, authentication.name)"
    )
    public String getPayment(
            @PathVariable Long id) {

        return "Payment Details";
    }
}