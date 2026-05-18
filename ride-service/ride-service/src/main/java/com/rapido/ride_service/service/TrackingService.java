package com.rapido.ride_service.service;

import com.rapido.ride_service.entity.DriverLocationEntity;
import com.rapido.ride_service.event.DriverLocationEvent;
import com.rapido.ride_service.event.RideStatusEvent;
import com.rapido.ride_service.repository.DriverLocationRepository;
import com.rapido.ride_service.util.EtaCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrackingService {

    private static final Logger logger =
            LoggerFactory.getLogger(TrackingService.class);

    private final SimpMessagingTemplate messagingTemplate;
    private final DriverLocationRepository driverLocationRepository;

    public TrackingService(
            SimpMessagingTemplate messagingTemplate,
            DriverLocationRepository driverLocationRepository) {

        this.messagingTemplate = messagingTemplate;
        this.driverLocationRepository = driverLocationRepository;
    }

    public void processLocation(DriverLocationEvent event) {

        if (event.getRideId() == null || event.getDriverId() == null) {
            throw new RuntimeException("Ride ID and Driver ID are required");
        }

        if (event.getLatitude() == null || event.getLongitude() == null) {
            throw new RuntimeException("Latitude and longitude are required");
        }

        if (event.getLatitude() < -90 || event.getLatitude() > 90) {
            throw new RuntimeException("Invalid latitude value");
        }

        if (event.getLongitude() < -180 || event.getLongitude() > 180) {
            throw new RuntimeException("Invalid longitude value");
        }

        event.setTimestamp(LocalDateTime.now());

        int eta = EtaCalculator.calculateEta(
                event.getLatitude(),
                event.getLongitude(),
                17.4000,
                78.5000
        );

        event.setEtaMinutes(eta);

        DriverLocationEntity location = new DriverLocationEntity();
        location.setRideId(event.getRideId());
        location.setDriverId(event.getDriverId());
        location.setLatitude(event.getLatitude());
        location.setLongitude(event.getLongitude());
        location.setEtaMinutes(event.getEtaMinutes());
        location.setTimestamp(event.getTimestamp());

        driverLocationRepository.save(location);

        messagingTemplate.convertAndSend(
                "/topic/ride/" + event.getRideId(),
                event
        );

        logger.info(
                "Live location saved and broadcasted for ride ID: {}",
                event.getRideId()
        );
    }

    public void sendRideStatusEvent(RideStatusEvent event) {

        event.setTimestamp(LocalDateTime.now());

        messagingTemplate.convertAndSend(
                "/topic/ride-status/" + event.getRideId(),
                event
        );

        logger.info(
                "Ride status event sent: {}",
                event.getStatus()
        );
    }

    public List<DriverLocationEntity> getRideTrackingHistory(Long rideId) {
        return driverLocationRepository.findByRideId(rideId);
    }
}