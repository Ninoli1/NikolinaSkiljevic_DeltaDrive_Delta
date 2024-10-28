package com.app.DeltaDrive.controller;

import com.app.DeltaDrive.dto.NearestVehicleDTO;
import com.app.DeltaDrive.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;


    @GetMapping("/nearest")
    public ResponseEntity<List<NearestVehicleDTO>> findNearestVehicles(
            @RequestParam Double passLat,
            @RequestParam Double passLong,
            @RequestParam Double destinationLat,
            @RequestParam Double destinationLong) {

        return ResponseEntity.ok(vehicleService.findNearestVehicles(passLat, passLong, destinationLat, destinationLong));
    }



}
