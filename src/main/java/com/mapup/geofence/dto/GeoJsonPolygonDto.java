package com.mapup.geofence.dto;

import lombok.Data;

import java.util.List;

@Data
public class GeoJsonPolygonDto {
    private String type;
    private List<List<List<Double>>> coordinates;
}
