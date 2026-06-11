package com.rapido.api_gateway.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scope")
public class ScopeController {

    @GetMapping("/ride")
    @PreAuthorize("hasAuthority('SCOPE_ride.read')")
    public String rideRead() {

        return "Ride Scope Access";
    }

    @GetMapping("/payment")
    @PreAuthorize("hasAuthority('SCOPE_payment.read')")
    public String paymentRead() {

        return "Payment Scope Access";
    }
}