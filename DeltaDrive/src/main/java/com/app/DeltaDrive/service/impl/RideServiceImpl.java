package com.app.DeltaDrive.service.impl;

import com.app.DeltaDrive.dto.RideDTO;
import com.app.DeltaDrive.mapper.RideDTOMapper;
import com.app.DeltaDrive.model.Location;
import com.app.DeltaDrive.model.Ride;
import com.app.DeltaDrive.model.Vehicle;
import com.app.DeltaDrive.model.enums.RideStatus;
import com.app.DeltaDrive.repository.RideRepository;
import com.app.DeltaDrive.service.AuthenticationService;
import com.app.DeltaDrive.service.CalculationService;
import com.app.DeltaDrive.service.RideService;
import com.app.DeltaDrive.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final VehicleService vehicleService;
    private final RideDTOMapper rideDTOMapper;
    private final CalculationService calculationService;
    private final AuthenticationService authService;
    @Override
    public Ride save(Ride ride) {
        try{
           return rideRepository.save(ride);
        }catch (IllegalArgumentException  |DataIntegrityViolationException e){
            throw new RuntimeException("Error saving ride"+e.getMessage(),e);
        }
    }

    public RideDTO findRideDTOById(Integer rideId) {
        return rideRepository.findById(rideId)
                .map(rideDTOMapper::mapRideToRideDTO)
                .orElseThrow(() -> new RuntimeException("Ride not found"));
    }

    public Ride findRideById(Integer rideId) {
       return rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));
    }


    public Ride finishRide(Integer rideId,Integer vehicleId){

       Ride oldRide= findRideById(rideId);
       oldRide.setStatus(RideStatus.COMPLETED);
       vehicleService.makeAvailable(vehicleId);
       return save(oldRide);
    }

    public Ride generateRide(Vehicle vehicle,
                              Location passengerLocation,
                              Location destinationLocation,
                               String loggedInEmail){
        double totalDistance= calculationService.calculateTotalDistance(vehicle,passengerLocation,destinationLocation);
        double totalPrice=calculationService.calculateTotalPrice(vehicle,passengerLocation,destinationLocation);

        Ride ride = new Ride();
        ride.setPassengerEmail(loggedInEmail);
        ride.setPassengerLocation(passengerLocation);
        ride.setDestinationLocation(destinationLocation);
        ride.setTotalPrice(totalPrice);
        ride.setTotalDistance(totalDistance);
        ride.setVehicleId(vehicle.getId());
        ride.setVehicleId(vehicle.getId());
        ride.setStatus(RideStatus.STARTED);

        return save(ride);
    }

    public List<RideDTO> findRidesByPassenger(){
        List<Ride> rides = rideRepository.findByPassengerEmail(authService.findLoggedInEmail());

        return rides.stream()
                .map(rideDTOMapper::mapRideToRideDTO)
                .collect(Collectors.toList());
    }
}
