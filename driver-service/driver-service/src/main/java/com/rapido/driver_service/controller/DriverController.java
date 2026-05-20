package com.rapido.driver_service.controller;

import com.rapido.driver_service.entity.Driver;

import com.rapido.driver_service.service.DriverService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(
            DriverService driverService
    ) {

        this.driverService =
                driverService;
    }

    // TEST API
    @GetMapping("/test")
    public String test() {

        return "Driver Service Working Successfully";
    }

    // CREATE DRIVER PROFILE
    @PostMapping("/profile")
    public Driver createDriverProfile(
            @RequestBody Driver driver
    ) {

        return driverService.addDriver(
                driver
        );
    }

    // GET AVAILABLE DRIVERS
    @GetMapping("/available")
    public List<Driver> getAvailableDrivers() {

        return driverService.getAvailableDrivers();
    }

    // UPDATE ONLINE STATUS
    @PutMapping("/online/{email}")
    public String updateOnlineStatus(
            @PathVariable String email,
            @RequestParam boolean online
    ) {

        return driverService.updateOnlineStatus(
                email,
                online
        );
    }
}