package com.rapido.ride_service.util;

public class EtaCalculator {

    // Approximate ETA calculation
    public static int calculateEta(
            double sourceLat,
            double sourceLon,
            double destLat,
            double destLon) {

        // Simple distance formula
        double distance =
                Math.sqrt(
                        Math.pow(destLat - sourceLat, 2)
                                +
                                Math.pow(destLon - sourceLon, 2)
                );

        // Assume average speed = 30 km/h
        double averageSpeed = 30.0;

        // Convert rough distance to ETA
        int etaMinutes =
                (int) ((distance * 111) / averageSpeed * 60);

        // Minimum ETA
        if (etaMinutes <= 0) {
            etaMinutes = 1;
        }

        return etaMinutes;
    }
}
