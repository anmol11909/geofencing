package com.mapup.geofence.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mapup.geofence.dto.GeofenceRequest;
import com.mapup.geofence.dto.GeofenceResponse;
import com.mapup.geofence.entity.Geofence;
import com.mapup.geofence.repository.GeofenceRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.mapup.geofence.dto.GetGeofencesResponse;
import com.mapup.geofence.dto.GeofenceDetailsResponse;

import java.util.stream.Collectors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GeofenceServiceImpl
        implements GeofenceService {

    private final GeofenceRepository repository;

    private final ObjectMapper objectMapper;

    @Override
    public GeofenceResponse createGeofence(
            GeofenceRequest request) {

        long start = System.nanoTime();

        validateCoordinates(
                request.getCoordinates()
        );

        try {

            String coordinatesJson =
                    objectMapper.writeValueAsString(
                            request.getCoordinates()
                    );

            Geofence geofence =
                    Geofence.builder()
                            .id("geo_" + UUID.randomUUID())
                            .name(request.getName())
                            .description(request.getDescription())
                            .category(request.getCategory())
                            .coordinates(coordinatesJson)
                            .createdAt(LocalDateTime.now())
                            .build();

            repository.save(geofence);

            return GeofenceResponse.builder()
                    .id(geofence.getId())
                    .name(geofence.getName())
                    .status("active")
                    .timeNs(System.nanoTime() - start)
                    .build();

        } catch (JsonProcessingException e) {

            throw new RuntimeException(
                    "Unable to process coordinates"
            );
        }
    }

    private void validateCoordinates(
            List<List<Double>> coordinates) {

        if (coordinates.size() < 4) {
            throw new RuntimeException(
                    "Minimum 4 coordinates required"
            );
        }

        List<Double> first = coordinates.get(0);

        List<Double> last =
                coordinates.get(
                        coordinates.size() - 1
                );

        if (!first.equals(last)) {
            throw new RuntimeException(
                    "Polygon must be closed"
            );
        }

        for (List<Double> point : coordinates) {

            Double latitude = point.get(0);
            Double longitude = point.get(1);

            if (latitude < -90 || latitude > 90) {
                throw new RuntimeException(
                        "Invalid latitude"
                );
            }

            if (longitude < -180 || longitude > 180) {
                throw new RuntimeException(
                        "Invalid longitude"
                );
            }
        }
    }

    @Override
    public GetGeofencesResponse getGeofences(
            String category) {

        long start = System.nanoTime();

        List<Geofence> geofences;

        if (category != null &&
                !category.isBlank()) {

            geofences =
                    repository.findByCategory(
                            category
                    );

        } else {

            geofences =
                    repository.findAll();
        }

        List<GeofenceDetailsResponse> responseList =
                geofences.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList());

        return GetGeofencesResponse.builder()
                .geofences(responseList)
                .timeNs(System.nanoTime() - start)
                .build();
    }

    private GeofenceDetailsResponse mapToResponse(
            Geofence geofence) {

        try {

            List<List<Double>> coordinates =
                    objectMapper.readValue(
                            geofence.getCoordinates(),
                            List.class
                    );

            return GeofenceDetailsResponse
                    .builder()
                    .id(geofence.getId())
                    .name(geofence.getName())
                    .description(
                            geofence.getDescription()
                    )
                    .coordinates(coordinates)
                    .category(
                            geofence.getCategory()
                    )
                    .createdAt(
                            geofence.getCreatedAt()
                    )
                    .build();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to parse coordinates"
            );
        }
    }
}
