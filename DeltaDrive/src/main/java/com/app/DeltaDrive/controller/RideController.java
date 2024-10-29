package com.app.DeltaDrive.controller;


import com.app.DeltaDrive.model.Ride;
import com.app.DeltaDrive.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;

    @GetMapping("/{rideId}")
    public ResponseEntity<Ride> getRide(@PathVariable Integer rideId) {
        Ride ride = rideService.findRideById(rideId);
        return ResponseEntity.ok(ride);
    }

    @GetMapping("/map")
    public String index() {
        return "index";
    }

}
