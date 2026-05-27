package com.rapido.driver_service.service;

import com.rapido.driver_service.dto.SurgePricingResponse;

import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class PricingService {

    public SurgePricingResponse calculateFare(
            Double distanceKm,
            Integer activeDrivers,
            Integer activeRides,
            Boolean traffic
    ) {

        double baseFare = 50 + (distanceKm * 12);

        double surgeMultiplier = 1.0;

        boolean peakHour = isPeakHour();

        boolean highDemand = false;

        boolean trafficApplied = false;

        // PEAK HOUR SURGE

        if (peakHour) {

            surgeMultiplier += 0.5;
        }

        // DEMAND SURGE

        if (activeRides > activeDrivers) {

            surgeMultiplier += 0.7;

            highDemand = true;
        }

        // TRAFFIC SURGE

        if (traffic) {

            surgeMultiplier += 0.3;

            trafficApplied = true;
        }

        double finalFare =
                baseFare * surgeMultiplier;

        SurgePricingResponse response =
                new SurgePricingResponse();

        response.setBaseFare(baseFare);

        response.setSurgeMultiplier(
                surgeMultiplier
        );

        response.setFinalFare(finalFare);

        response.setPeakHour(peakHour);

        response.setHighDemand(highDemand);

        response.setTrafficApplied(
                trafficApplied
        );

        response.setMessage(
                "Dynamic fare calculated successfully"
        );

        return response;
    }

    // PEAK HOUR CHECK

    private boolean isPeakHour() {

        LocalTime now = LocalTime.now();

        return (
                (now.isAfter(LocalTime.of(8, 0))
                        &&
                        now.isBefore(LocalTime.of(11, 0)))
                        ||
                        (now.isAfter(LocalTime.of(17, 0))
                                &&
                                now.isBefore(LocalTime.of(21, 0)))
        );
    }
}
