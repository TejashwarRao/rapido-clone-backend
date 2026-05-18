package com.rapido.driver_service.service;

import com.rapido.driver_service.dto.DriverProfileDTO;
import com.rapido.driver_service.entity.Driver;
import com.rapido.driver_service.repository.DriverRepository;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public DriverProfileDTO getProfile(String email) {

        Driver driver = driverRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Driver profile not found"));

        DriverProfileDTO dto = new DriverProfileDTO();

        dto.setFullName(driver.getFullName());
        dto.setEmail(driver.getEmail());
        dto.setPhone(driver.getPhone());
        dto.setVehicleNumber(driver.getVehicleNumber());
        dto.setVehicleModel(driver.getVehicleModel());
        dto.setVehicleType(driver.getVehicleType());

        return dto;
    }

    public void updateProfile(String email, DriverProfileDTO dto) {

        Driver driver = driverRepository.findByEmail(email)
                .orElseGet(Driver::new);

        driver.setEmail(email);
        driver.setFullName(dto.getFullName());
        driver.setPhone(dto.getPhone());
        driver.setVehicleNumber(dto.getVehicleNumber());
        driver.setVehicleModel(dto.getVehicleModel());
        driver.setVehicleType(dto.getVehicleType());

        driverRepository.save(driver);
    }

    public void updateAvailability(String email, Boolean available) {

        Driver driver = driverRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driver.setAvailable(available);

        driverRepository.save(driver);
    }

    public void updateOnlineStatus(String email, Boolean online) {

        Driver driver = driverRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driver.setOnline(online);

        driverRepository.save(driver);
    }

    public void updateLocation(String email,
                               Double latitude,
                               Double longitude) {

        Driver driver = driverRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driver.setCurrentLatitude(latitude);
        driver.setCurrentLongitude(longitude);

        driverRepository.save(driver);
    }
}