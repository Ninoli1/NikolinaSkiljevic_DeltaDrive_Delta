package com.app.DeltaDrive.dto;

import com.app.DeltaDrive.model.Location;

public record BookingRequestDTO( Integer vehicleId,
                                 String email,
                                 Location passengerLocation,
                                 Location destinationLocation) {
}
