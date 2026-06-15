package com.mapup.geofence.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapup.geofence.dto.GeoJsonPolygonDto;
import com.mapup.geofence.dto.LocationUpdateRequestDto;
import com.mapup.geofence.entity.Geofence;
import com.mapup.geofence.entity.GeofenceEvent;
import com.mapup.geofence.enums.EventType;
import com.mapup.geofence.repository.GeofenceRepository;
import com.mapup.geofence.util.PolygonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mapup.geofence.dto.VehicleRequestDto;
import com.mapup.geofence.dto.VehicleResponseDto;
import com.mapup.geofence.entity.Vehicle;
import com.mapup.geofence.repository.VehicleRepository;
import com.mapup.geofence.repository.GeofenceEventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService{

    private final VehicleRepository vehicleRepository;

    private final GeofenceRepository geofenceRepository;

    private final GeofenceEventRepository geofenceEventRepository;

    private final ObjectMapper objectMapper;

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

    @Override
    public String updateLocation(
            Long vehicleId,
            LocationUpdateRequestDto requestDto) {

        Vehicle vehicle = vehicleRepository
                .findById(vehicleId)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found"));

        vehicle.setCurrentLatitude(
                requestDto.getLatitude());

        vehicle.setCurrentLongitude(
                requestDto.getLongitude());

        List<Geofence> geofences =
                geofenceRepository.findAll();

        Geofence matchedGeofence = null;

        for (Geofence geofence : geofences) {

            try {

                GeoJsonPolygonDto polygonDto =
                        objectMapper.readValue(
                                geofence.getCoordinates(),
                                GeoJsonPolygonDto.class);

                List<List<Double>> polygon =
                        polygonDto.getCoordinates().get(0);

                boolean inside =
                        PolygonUtil.isPointInsidePolygon(
                                requestDto.getLatitude(),
                                requestDto.getLongitude(),
                                polygon);

                if (inside) {

                    matchedGeofence = geofence;

                    break;
                }

            } catch (Exception e) {

                throw new RuntimeException(
                        "Unable to parse geofence coordinates",
                        e);
            }
        }

        String previousGeofenceId =
                vehicle.getCurrentGeofenceId();

        String currentGeofenceId =
                matchedGeofence == null
                        ? null
                        : matchedGeofence.getId();

        // OUTSIDE -> INSIDE
        if (previousGeofenceId == null
                && currentGeofenceId != null) {

            saveEvent(
                    vehicle,
                    matchedGeofence,
                    EventType.ENTER);
        }

        // INSIDE -> OUTSIDE
        else if (previousGeofenceId != null
                && currentGeofenceId == null) {

            Geofence previousGeofence =
                    geofenceRepository
                            .findById(previousGeofenceId)
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Geofence not found"));

            saveEvent(
                    vehicle,
                    previousGeofence,
                    EventType.EXIT);
        }

        vehicle.setCurrentGeofenceId(
                currentGeofenceId);

        vehicleRepository.save(vehicle);

        return "Location updated successfully";
    }

    private void saveEvent(
            Vehicle vehicle,
            Geofence geofence,
            EventType eventType) {

        GeofenceEvent event =
                GeofenceEvent.builder()
                        .vehicle(vehicle)
                        .geofence(geofence)
                        .eventType(eventType)
                        .build();

        geofenceEventRepository.save(event);
    }
}
