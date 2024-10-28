package com.app.DeltaDrive.service;

import com.app.DeltaDrive.dto.NearestVehicleDTO;
import com.app.DeltaDrive.model.Vehicle;

import java.util.List;

public interface VehicleService {

    List<NearestVehicleDTO> findNearestVehicles(Double passengerLatitude, Double passengerLongitude,Double destinationLatitude,Double destinationLongitude);
}
