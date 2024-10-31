package com.app.DeltaDrive.service.impl;

import com.app.DeltaDrive.dto.BookingRequestDTO;
import com.app.DeltaDrive.model.Location;
import com.app.DeltaDrive.model.Ride;
import com.app.DeltaDrive.model.Vehicle;
import com.app.DeltaDrive.model.enums.Availability;
import com.app.DeltaDrive.model.enums.RideStatus;
import com.app.DeltaDrive.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final VehicleService vehicleService;

    private final RideService rideService;

    private final CalculationService calculationService;

    private final AuthenticationService authService;



    public String bookAVehicle(BookingRequestDTO request){


        Vehicle selectedVehicle=vehicleService.findById(request.vehicleId());

        Random random = new Random();
        boolean driverAccepted = random.nextInt(100) > 25;
        if (!driverAccepted) {
            String rejected= new String("Driver rejected!");
            return rejected;
        }

        selectedVehicle.setAvailability(Availability.BOOKED);
        vehicleService.save(selectedVehicle);

        Ride createdRide=generateRide(selectedVehicle,request.passengerLocation(), request.destinationLocation());

        String accepted= new String("Driver accepted! Visit http://localhost:8080/index.html?id="+createdRide.getId());

        return accepted;
    }

    private Ride generateRide(Vehicle vehicle,
                              Location passengerLocation,
                              Location destinationLocation){
        double totalDistance= calculationService.calculateTotalDistance(vehicle,passengerLocation,destinationLocation);
        double totalPrice=calculationService.calculateTotalPrice(vehicle,passengerLocation,destinationLocation);

        Ride ride = new Ride();
        ride.setPassengerEmail(authService.findLoggedInEmail());
        ride.setPassengerLocation(passengerLocation);
        ride.setDestinationLocation(destinationLocation);
        ride.setTotalPrice(totalPrice);
        ride.setTotalDistance(totalDistance);
        ride.setVehicleId(vehicle.getId());
        ride.setVehicleId(vehicle.getId());
        ride.setStatus(RideStatus.STARTED);

       return rideService.save(ride);
    }



}
