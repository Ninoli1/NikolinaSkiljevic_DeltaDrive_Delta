package com.app.DeltaDrive.service;

import com.app.DeltaDrive.dto.RideDTO;
import com.app.DeltaDrive.model.Ride;

public interface RideService {

    public Ride save(Ride ride);

    public RideDTO findRideDTOById(Integer rideId);

    public Ride finishRide(Integer rideId,Integer vehicleId);
    public Ride findRideById(Integer rideId);
}
