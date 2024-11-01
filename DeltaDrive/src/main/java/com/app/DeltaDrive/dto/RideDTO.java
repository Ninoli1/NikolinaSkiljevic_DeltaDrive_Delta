package com.app.DeltaDrive.dto;


import com.app.DeltaDrive.model.Location;
import com.app.DeltaDrive.model.enums.RideStatus;
import jakarta.persistence.*;


public record RideDTO (
        Integer id,
        String passengerEmail,
        Location vehicleLocation,
        Location destinationLocation,
        Location passengerLocation,
        Double totalPrice,
        Double totalDistance,
        String driver,
        Integer vehicleId,
        String status

) {

}

