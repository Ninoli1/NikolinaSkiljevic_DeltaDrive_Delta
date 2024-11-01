package com.app.DeltaDrive.service.impl;

import com.app.DeltaDrive.dto.BookingRequestDTO;
import com.app.DeltaDrive.model.Location;
import com.app.DeltaDrive.model.Ride;
import com.app.DeltaDrive.model.Vehicle;
import com.app.DeltaDrive.model.enums.Availability;
import com.app.DeltaDrive.model.enums.RideStatus;
import com.app.DeltaDrive.service.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
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
    private final AuthenticationService authService;

    @PersistenceContext
    private EntityManager entityManager;

    /*
    Transaction with pessimistic lock is used in case multiple passengers
    try to book the same vehicle at the same time. This means that while driver
    is deciding whether he's gonna accept the request of one's passenger, others'
    requests are on lower level, waiting for the driver to decide.
     */
    @Transactional
    public String bookAVehicle(BookingRequestDTO request){
        Vehicle selectedVehicle=entityManager.find(Vehicle.class,request.vehicleId(), LockModeType.PESSIMISTIC_WRITE);


        if (selectedVehicle.getAvailability() != Availability.AVAILABLE) {
            return "Vehicle already booked";
        }

        Random random = new Random();
        boolean driverAccepted = random.nextInt(100) > 25;
        if (!driverAccepted) {
            String rejected= new String("Driver rejected!");
            return rejected;
        }

        selectedVehicle.setAvailability(Availability.BOOKED);
        vehicleService.save(selectedVehicle);

        Ride createdRide=rideService.generateRide(selectedVehicle,
                                                  request.passengerLocation(),
                                                  request.destinationLocation(),
                                                  authService.findLoggedInEmail());

        String accepted= new String("Driver accepted! Visit http://localhost:8080/index.html?id="+createdRide.getId());

        return accepted;
    }
}
