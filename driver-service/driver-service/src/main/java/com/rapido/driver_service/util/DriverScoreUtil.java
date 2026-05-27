package com.rapido.driver_service.util;

import com.rapido.driver_service.entity.Driver;

public class DriverScoreUtil {

    public static double calculateScore(
            Driver driver,
            double distance
    ) {

        double distanceScore =
                100 - distance;

        double ratingScore =
                driver.getRating() * 20;

        double acceptanceScore =
                driver.getAcceptanceRate();

        double cancellationPenalty =
                driver.getCancellationRate() * 2;

        double loadPenalty =
                driver.getCurrentLoad() * 5;

        return
                distanceScore
                        + ratingScore
                        + acceptanceScore
                        - cancellationPenalty
                        - loadPenalty;
    }
}