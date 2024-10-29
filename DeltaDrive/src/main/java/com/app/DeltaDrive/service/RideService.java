package com.app.DeltaDrive.service;

import com.app.DeltaDrive.model.Ride;

public interface RideService {

    public Ride save(Ride ride);

    public Ride findRideById(Integer rideId);
}
