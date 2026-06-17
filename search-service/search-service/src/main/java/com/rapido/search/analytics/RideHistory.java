package com.rapido.search.analytics;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RideHistory {

    private Long userId;

    private String pickup;

    private String destination;

    private String rideType;

    private LocalDateTime rideTime;
}