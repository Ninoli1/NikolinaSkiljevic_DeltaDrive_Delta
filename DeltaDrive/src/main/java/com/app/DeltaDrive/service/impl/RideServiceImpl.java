package com.app.DeltaDrive.service.impl;

import com.app.DeltaDrive.model.Ride;
import com.app.DeltaDrive.repository.RideRepository;
import com.app.DeltaDrive.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    @Override
    public Ride save(Ride ride) {
        try{
           return rideRepository.save(ride);
        }catch (IllegalArgumentException  |DataIntegrityViolationException e){
            throw new RuntimeException("Error saving ride"+e.getMessage(),e);
        }
    }

    public Ride findRideById(Integer rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));
    }
}
