package com.rapido.search.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "locations")
public class LocationDocument {

    @Id
    private Long id;

    private String name;

    private String type;
}