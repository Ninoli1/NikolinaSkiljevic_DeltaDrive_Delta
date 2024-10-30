package com.app.DeltaDrive.service.impl;

import com.app.DeltaDrive.dto.BookingRequestDTO;
import com.app.DeltaDrive.model.Location;
import com.app.DeltaDrive.model.Ride;
import com.app.DeltaDrive.model.Vehicle;
import com.app.DeltaDrive.model.enums.Availability;
import com.app.DeltaDrive.model.enums.RideStatus;
import com.app.DeltaDrive.repository.RideRepository;
import com.app.DeltaDrive.repository.UserRepository;
import com.app.DeltaDrive.service.BookingService;
import com.app.DeltaDrive.service.CalculationService;
import com.app.DeltaDrive.service.RideService;
import com.app.DeltaDrive.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final VehicleService vehicleService;

    private final RideService rideService;

    private final CalculationService calculationService;



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

        generateRide(selectedVehicle,request.passengerLocation(), request.destinationLocation(), request.email());

        String accepted= new String("Driver accepted!");

        return accepted;
    }

    private void generateRide(Vehicle vehicle,
                              Location passengerLocation,
                              Location destinationLocation,
                              String passengerEmail){
        double distancePassengerDestination= calculationService.calculateDistance(passengerLocation,destinationLocation);
        double distanceVehiclePassenger= calculationService.calculateDistance(vehicle.getLocation(),passengerLocation);
        double totalDistance= distanceVehiclePassenger+distancePassengerDestination;
        double totalPrice=calculationService.calculateTotalPrice(vehicle,totalDistance);

        Ride ride = new Ride();
        ride.setPassengerEmail(passengerEmail);
        ride.setStartLocation(passengerLocation);
        ride.setEndLocation(destinationLocation);
        ride.setTotalPrice(totalPrice);
        ride.setDriver(vehicle.getFirstName()+vehicle.getLastName());
        ride.setStatus(RideStatus.STARTED);

        rideService.save(ride);
    }


}
