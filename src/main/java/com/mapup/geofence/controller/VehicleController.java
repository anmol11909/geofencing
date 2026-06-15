package com.mapup.geofence.controller;
import com.mapup.geofence.dto.VehicleRequestDto;
import com.mapup.geofence.dto.VehicleResponseDto;
import com.mapup.geofence.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicles")

public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping
    public VehicleResponseDto createVehicle(
            @RequestBody VehicleRequestDto requestDto) {

        return vehicleService.createVehicle(requestDto);
    }

    @GetMapping
    public List<VehicleResponseDto> getAllVehicles() {

        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public VehicleResponseDto getVehicleById(
            @PathVariable Long id) {

        return vehicleService.getVehicleById(id);
    }
}
