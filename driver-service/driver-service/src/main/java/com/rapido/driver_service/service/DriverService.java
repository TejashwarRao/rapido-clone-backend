package com.rapido.driver_service.service;

import com.rapido.driver_service.dto.NearbyDriverResponse;
import com.rapido.driver_service.entity.Driver;
import com.rapido.driver_service.repository.DriverRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public List<NearbyDriverResponse> findNearbyDrivers(
            Double latitude,
            Double longitude,
            Double radius
    ) {

        List<Driver> drivers =
                driverRepository.findAll();

        List<NearbyDriverResponse> response =
                new ArrayList<>();

        for (Driver driver : drivers) {

            NearbyDriverResponse dto =
                    new NearbyDriverResponse();

            dto.setId(driver.getId());
            dto.setName(driver.getName());
            dto.setLatitude(driver.getLatitude());
            dto.setLongitude(driver.getLongitude());
            dto.setRating(driver.getRating());

            response.add(dto);
        }

        return response;
    }
}