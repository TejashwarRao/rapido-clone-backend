package com.rapido.auth_server.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rides")
public class RideController {

    @GetMapping("/{id}")
    @PreAuthorize(
            "@abacService.canAccessRide(#id, authentication.name)"
    )
    public String getRide(
            @PathVariable Long id) {

        return "Ride Details";
    }
}