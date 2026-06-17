package com.rapido.search.service;

import com.rapido.search.document.DriverDocument;
import com.rapido.search.repository.DriverSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeoSearchService {

    private final DriverSearchRepository repository;

    public List<DriverDocument> nearbyDrivers(
            Double latitude,
            Double longitude,
            Double radiusKm) {

        List<DriverDocument> result = new ArrayList<>();

        repository.findAll().forEach(driver -> {

            if (driver.getLocation() == null) {
                return;
            }

            double distance =
                    calculateDistance(
                            latitude,
                            longitude,
                            driver.getLocation().getLat(),
                            driver.getLocation().getLon()
                    );

            if (distance <= radiusKm) {
                result.add(driver);
            }
        });

        return result;
    }

    private double calculateDistance(
            double lat1,
            double lon1,
            double lat2,
            double lon2) {

        final int EARTH_RADIUS = 6371;

        double latDistance =
                Math.toRadians(lat2 - lat1);

        double lonDistance =
                Math.toRadians(lon2 - lon1);

        double a =
                Math.sin(latDistance / 2)
                        * Math.sin(latDistance / 2)
                        + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2)
                        * Math.sin(lonDistance / 2);

        double c =
                2 * Math.atan2(
                        Math.sqrt(a),
                        Math.sqrt(1 - a)
                );

        return EARTH_RADIUS * c;
    }
}