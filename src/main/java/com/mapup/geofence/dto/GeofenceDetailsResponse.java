package com.mapup.geofence.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class GeofenceDetailsResponse {

    private String id;

    private String name;

    private String description;

    private List<List<Double>> coordinates;

    private String category;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
