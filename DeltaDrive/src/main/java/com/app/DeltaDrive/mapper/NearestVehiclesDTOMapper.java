package com.app.DeltaDrive.mapper;

import com.app.DeltaDrive.dto.NearestVehicleDTO;
import com.app.DeltaDrive.model.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class NearestVehiclesDTOMapper {

    public NearestVehicleDTO mapToDTO(Vehicle vehicle,Double distancePassengerVehicle,Double totalPrice,Double distancePassengerDestination) {
        return new NearestVehicleDTO(
                vehicle.getId(),
               vehicle.getBrand(),
                distancePassengerVehicle,
                distancePassengerDestination,
                totalPrice,
                vehicle.getAvailability().toString()
        );
    }

}
