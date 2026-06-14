package com.mapup.geofence.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
public class GetGeofencesResponse {

    private List<GeofenceDetailsResponse> geofences;

    @JsonProperty("time_ns")
    private long timeNs;
}