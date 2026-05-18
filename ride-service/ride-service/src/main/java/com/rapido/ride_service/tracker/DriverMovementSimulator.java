package com.rapido.ride_service.tracker;

import com.rapido.ride_service.event.DriverLocationEvent;
import com.rapido.ride_service.service.TrackingService;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class DriverMovementSimulator {

    private final TrackingService trackingService;

    public DriverMovementSimulator(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    public void startSimulation(Long rideId, Long driverId) {

        ScheduledExecutorService executorService =
                Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(() -> {

            DriverLocationEvent event =
                    new DriverLocationEvent();

            event.setRideId(rideId);
            event.setDriverId(driverId);

            // Simulated moving coordinates
            double latitude =
                    17.3850 + Math.random() / 100;

            double longitude =
                    78.4867 + Math.random() / 100;

            event.setLatitude(latitude);
            event.setLongitude(longitude);

            trackingService.processLocation(event);

            System.out.println(
                    "Simulated Driver Location Sent"
            );

        }, 0, 5, TimeUnit.SECONDS);
    }
}