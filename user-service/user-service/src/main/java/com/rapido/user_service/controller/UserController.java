package com.rapido.user_service.controller;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    // PUBLIC API
    @GetMapping("/users/public/test")
    public String publicApi() {

        return "Public API";
    }

    // USER API
    @GetMapping("/users/profile")
    public String userProfile(
            Authentication authentication
    ) {

        return "USER PROFILE : "
                + authentication.getName();
    }

    // DRIVER API
    @GetMapping("/drivers/dashboard")
    public String driverDashboard(
            Authentication authentication
    ) {

        return "DRIVER DASHBOARD : "
                + authentication.getName();
    }

    // ADMIN API
    @GetMapping("/admin/panel")
    public String adminPanel(
            Authentication authentication
    ) {

        return "ADMIN PANEL : "
                + authentication.getName();
    }
}