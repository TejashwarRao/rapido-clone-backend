package com.rapido.api_gateway.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class PublicController {

    @GetMapping("/public")
    public String publicApi() {

        return "Public API";
    }
}