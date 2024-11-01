package com.app.DeltaDrive.dto;

import com.app.DeltaDrive.model.Location;

public record BookingRequestDTO( Integer vehicleId,
                                 Location passengerLocation,
                                 Location destinationLocation) {
}
