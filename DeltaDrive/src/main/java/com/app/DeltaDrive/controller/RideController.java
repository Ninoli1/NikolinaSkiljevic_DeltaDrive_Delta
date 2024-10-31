package com.app.DeltaDrive.controller;


import com.app.DeltaDrive.dto.RideDTO;
import com.app.DeltaDrive.model.Ride;
import com.app.DeltaDrive.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;

    @GetMapping("/{rideId}")
    public ResponseEntity<RideDTO> findRideById(@PathVariable Integer rideId) {
        return ResponseEntity.ok(rideService.findRideDTOById((rideId)));
    }

    @PutMapping("/finish/{id}/{vehicleId}")
    public ResponseEntity<Ride> finishRide(
             @PathVariable("id") Integer rideId, @PathVariable("vehicleId") Integer vehicleId) {
        return ResponseEntity.ok(rideService.finishRide(rideId,vehicleId));
    }

    @GetMapping("/myRides")
    public ResponseEntity<List<RideDTO>> findMyRides() {
        return ResponseEntity.ok(rideService.findRidesByPassenger());
    }



}
