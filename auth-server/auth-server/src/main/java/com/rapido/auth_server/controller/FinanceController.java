package com.rapido.auth_server.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FinanceController {

    @GetMapping("/finance")
    @PreAuthorize("hasRole('FINANCE_ADMIN')")
    public String finance() {

        return "Finance Access Granted";
    }
}