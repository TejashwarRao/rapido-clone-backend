package com.rapido.analytics_service.quality;

import com.rapido.analytics_service.dto.events.RideCompletedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataQualityCheck {

    public boolean validateRideEvent(
            RideCompletedEvent event
    ) {

        if (event == null) {
            return false;
        }

        if (event.getRideId() == null) {
            return false;
        }

        if (event.getUserId() == null) {
            return false;
        }

        if (event.getDriverId() == null) {
            return false;
        }

        if (event.getFare() == null) {
            return false;
        }

        if (event.getFare() < 0) {
            return false;
        }

        return true;
    }
}