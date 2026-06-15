package com.mapup.geofence.service;
import com.mapup.geofence.dto.LocationUpdateRequestDto;
import com.mapup.geofence.dto.VehicleRequestDto;
import com.mapup.geofence.dto.VehicleResponseDto;

import java.util.List;

public interface VehicleService {
    VehicleResponseDto createVehicle(VehicleRequestDto requestDto);

    List<VehicleResponseDto> getAllVehicles();

    VehicleResponseDto getVehicleById(Long id);

    String updateLocation(
            Long vehicleId,
            LocationUpdateRequestDto requestDto);


}
