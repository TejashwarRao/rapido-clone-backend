package com.rapido.auth_server.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','DRIVER','SUPER_ADMIN')")
    public String user() {

        return "User Access Granted";
    }
}