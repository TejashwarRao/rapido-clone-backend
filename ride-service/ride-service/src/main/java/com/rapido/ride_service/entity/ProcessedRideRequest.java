package com.rapido.ride_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "processed_ride_requests")
public class ProcessedRideRequest {

    @Id
    private String requestId;

    public ProcessedRideRequest() {
    }

    public ProcessedRideRequest(
            String requestId
    ) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(
            String requestId
    ) {
        this.requestId = requestId;
    }
}