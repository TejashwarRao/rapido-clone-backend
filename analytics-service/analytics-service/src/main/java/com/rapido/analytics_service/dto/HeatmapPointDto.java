package com.rapido.analytics_service.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeatmapPointDto {

    private Double latitude;

    private Double longitude;

    private Long rideCount;
}