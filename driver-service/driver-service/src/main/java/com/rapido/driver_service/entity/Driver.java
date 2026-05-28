package com.rapido.driver_service.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "drivers")
@Getter
@Setter
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    @Column(name = "vehicle_number")
    private String vehicleNumber;

    private Boolean available;

    private Double latitude;

    private Double longitude;

    private Double rating;

    @Column(name = "acceptance_rate")
    private Double acceptanceRate;

    @Column(name = "cancellation_rate")
    private Double cancellationRate;

    @Column(name = "current_load")
    private Integer currentLoad;
}