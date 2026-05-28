package com.rapido.driver_service.service;

import com.rapido.driver_service.dto.EtaResponse;

import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class EtaService {

    public EtaResponse calculateEta(
            Double distanceKm,
            Boolean traffic,
            Boolean rain
    ) {

        double averageSpeed = 40.0;

        boolean peakHour = isPeakHour();

        // TRAFFIC EFFECT

        if (traffic) {

            averageSpeed -= 10;
        }

        // RAIN EFFECT

        if (rain) {

            averageSpeed -= 5;
        }

        // PEAK HOUR EFFECT

        if (peakHour) {

            averageSpeed -= 8;
        }

        // MINIMUM SPEED SAFETY

        if (averageSpeed < 10) {

            averageSpeed = 10;
        }

        int etaMinutes =
                (int) ((distanceKm / averageSpeed) * 60);

        EtaResponse response =
                new EtaResponse();

        response.setDistanceKm(distanceKm);

        response.setAverageSpeed(averageSpeed);

        response.setEstimatedMinutes(
                etaMinutes
        );

        response.setTrafficApplied(traffic);

        response.setPeakHourApplied(
                peakHour
        );

        response.setMessage(
                "ETA calculated successfully"
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
