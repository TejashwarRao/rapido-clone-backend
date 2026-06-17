package com.rapido.search.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverUpdatedEvent {

    private Long driverId;

    private String name;

    private Double rating;

    private String vehicleType;

    private Double latitude;

    private Double longitude;

    private Boolean available;
}