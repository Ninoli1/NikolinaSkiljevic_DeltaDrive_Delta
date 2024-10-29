package com.app.DeltaDrive.mapper;

import com.app.DeltaDrive.dto.NearestVehicleDTO;
import com.app.DeltaDrive.model.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class NearestVehiclesDTOMapper {

    public NearestVehicleDTO mapToDTO(Vehicle vehicle,Double distance,Double totalPrice) {
        return new NearestVehicleDTO(
                vehicle.getId(),
               vehicle.getBrand(),
                distance,
                totalPrice,
                vehicle.getAvailability().toString()
        );
    }

}
