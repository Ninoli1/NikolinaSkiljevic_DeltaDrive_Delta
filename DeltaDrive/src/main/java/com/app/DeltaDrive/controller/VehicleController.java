package com.app.DeltaDrive.controller;

import com.app.DeltaDrive.dto.NearestVehicleDTO;
import com.app.DeltaDrive.model.Location;
import com.app.DeltaDrive.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;


    @GetMapping("/nearest")
    public ResponseEntity<List<NearestVehicleDTO>> findNearestVehicles(
            @RequestParam double passengerLatitude,
            @RequestParam double passengerLongitude,
            @RequestParam double destinationLatitude,
            @RequestParam double destinationLongitude) {

        Location passengerLocation = new Location(passengerLatitude, passengerLongitude);
        Location destinationLocation = new Location(destinationLatitude, destinationLongitude);

        return ResponseEntity.ok(vehicleService.findNearestVehicles(passengerLocation,destinationLocation));
    }







}
