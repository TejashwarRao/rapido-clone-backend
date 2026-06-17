package com.rapido.search.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "drivers")
public class DriverDocument {

    @Id
    private Long driverId;

    private String name;

    private Double rating;

    private String vehicleType;

    @GeoPointField
    private GeoPoint location;

    private Boolean available;
}