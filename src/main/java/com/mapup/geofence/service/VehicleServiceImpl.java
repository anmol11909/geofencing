package com.mapup.geofence.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mapup.geofence.dto.VehicleRequestDto;
import com.mapup.geofence.dto.VehicleResponseDto;
import com.mapup.geofence.entity.Vehicle;
import com.mapup.geofence.repository.VehicleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService{

    private final VehicleRepository vehicleRepository;

    @Override
    public VehicleResponseDto createVehicle(VehicleRequestDto requestDto) {

        Vehicle vehicle = Vehicle.builder()
                .vehicleNumber(requestDto.getVehicleNumber())
                .driverName(requestDto.getDriverName())
                .build();

        vehicle = vehicleRepository.save(vehicle);

        return mapToResponse(vehicle);
    }

    @Override
    public List<VehicleResponseDto> getAllVehicles() {

        return vehicleRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public VehicleResponseDto getVehicleById(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        return mapToResponse(vehicle);
    }

    private VehicleResponseDto mapToResponse(Vehicle vehicle) {

        return VehicleResponseDto.builder()
                .id(vehicle.getId())
                .vehicleNumber(vehicle.getVehicleNumber())
                .driverName(vehicle.getDriverName())
                .currentLatitude(vehicle.getCurrentLatitude())
                .currentLongitude(vehicle.getCurrentLongitude())
                .createdAt(vehicle.getCreatedAt())
                .build();
    }
}
