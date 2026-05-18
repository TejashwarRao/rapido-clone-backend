package com.rapido.ride_service.algorithm;

import com.rapido.ride_service.entity.Driver;
import com.rapido.ride_service.util.DistanceUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DriverMatchingAlgorithm {

    public Driver findNearestDriver(
            List<Driver> drivers,
            Double pickupLatitude,
            Double pickupLongitude
    ) {

        Driver nearestDriver = null;

        double minimumDistance =
                Double.MAX_VALUE;

        for (Driver driver : drivers) {

            double distance =
                    DistanceUtil.calculateDistance(
                            pickupLatitude,
                            pickupLongitude,
                            driver.getCurrentLatitude(),
                            driver.getCurrentLongitude()
                    );

            if (distance < minimumDistance) {

                minimumDistance = distance;
                nearestDriver = driver;
            }
        }

        return nearestDriver;
    }
}