package com.mapup.geofence.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GeofenceResponse {

    private String id;

    private String name;

    private String status;

    @JsonProperty("time_ns")
    private long timeNs;
}
