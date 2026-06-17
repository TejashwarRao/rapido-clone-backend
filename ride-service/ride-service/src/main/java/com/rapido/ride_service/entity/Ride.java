package com.rapido.ride_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rides")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String userEmail;

    private String driverEmail;

    private String pickupLocation;

    private String dropLocation;

    private Double fare;

    @Enumerated(EnumType.STRING)
    private RideStatus status;

    // GETTERS

    public Long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public Double getFare() {
        return fare;
    }

    public RideStatus getStatus() {
        return status;
    }

    // SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }
}