package com.rapido.auth_server.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {

    @GetMapping("/driver")
    @PreAuthorize("hasRole('DRIVER')")
    public String driver() {

        return "Driver Access Granted";
    }
}