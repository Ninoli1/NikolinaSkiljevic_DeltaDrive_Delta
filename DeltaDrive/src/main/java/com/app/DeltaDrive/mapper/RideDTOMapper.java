package com.app.DeltaDrive.mapper;


import com.app.DeltaDrive.dto.RideDTO;
import com.app.DeltaDrive.model.Ride;
import com.app.DeltaDrive.model.enums.RideStatus;
import com.app.DeltaDrive.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideDTOMapper {

    private final VehicleService vehicleService;
    public Ride mapRideDTOToRide(RideDTO rideDTO){

        Ride ride = new Ride();

        ride.setId(rideDTO.id());
        ride.setPassengerEmail(rideDTO.passengerEmail());
        ride.setDestinationLocation(rideDTO.destinationLocation());
        ride.setPassengerLocation(rideDTO.passengerLocation());
        ride.setTotalPrice(rideDTO.totalPrice());
        ride.setTotalDistance(rideDTO.totalDistance());
        ride.setVehicleId(rideDTO.vehicleId());
        ride.setStatus(RideStatus.valueOf(rideDTO.status()));
        return ride;
    }

    public RideDTO mapRideToRideDTO(Ride ride) {
        return new RideDTO(
                ride.getId(),
                ride.getPassengerEmail(),
                vehicleService.findVehicleLocation(ride.getVehicleId()),
                ride.getDestinationLocation(),
                ride.getPassengerLocation(),
                ride.getTotalPrice(),
                ride.getTotalDistance(),
                vehicleService.findDriver(ride.getVehicleId()),
                ride.getVehicleId(),
                ride.getStatus() != null ? ride.getStatus().name() : null
        );
    }

}
