package com.app.DeltaDrive.service;

import com.app.DeltaDrive.dto.RideDTO;
import com.app.DeltaDrive.model.Location;
import com.app.DeltaDrive.model.Ride;
import com.app.DeltaDrive.model.Vehicle;

import java.util.List;

public interface RideService {

     Ride save(Ride ride);

     RideDTO findRideDTOById(Integer rideId);

     Ride finishRide(Integer rideId,Integer vehicleId);
     Ride findRideById(Integer rideId);

     Ride generateRide(Vehicle vehicle,
                              Location passengerLocation,
                              Location destinationLocation,
                              String loggedInEmail);

     public List<RideDTO> findRidesByPassenger();
}
