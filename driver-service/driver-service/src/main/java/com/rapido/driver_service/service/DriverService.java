package com.rapido.driver_service.service;

import com.rapido.driver_service.entity.Driver;

import com.rapido.driver_service.repository.DriverRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(
            DriverRepository driverRepository
    ) {

        this.driverRepository =
                driverRepository;
    }

    // CREATE DRIVER
    public Driver addDriver(
            Driver driver
    ) {

        return driverRepository.save(driver);
    }

    // AVAILABLE DRIVERS
    public List<Driver> getAvailableDrivers() {

        return driverRepository.findByAvailableTrue();
    }

    // UPDATE ONLINE STATUS
    public String updateOnlineStatus(
            String email,
            boolean online
    ) {

        Driver driver =
                driverRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Driver not found"
                                )
                        );

        driver.setAvailable(online);

        driverRepository.save(driver);

        return "Driver status updated successfully";
    }
}