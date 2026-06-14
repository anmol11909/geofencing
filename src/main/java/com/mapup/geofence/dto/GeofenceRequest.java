package com.mapup.geofence.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GeofenceRequest {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private List<List<Double>> coordinates;

    @NotBlank
    private String category;
}