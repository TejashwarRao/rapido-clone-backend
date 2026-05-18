package com.rapido.ride_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private Boolean available;

    private Boolean online;

    private Double currentLatitude;

    private Double currentLongitude;
}