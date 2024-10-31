package com.app.DeltaDrive.service;

import com.app.DeltaDrive.dto.NearestVehicleDTO;
import com.app.DeltaDrive.model.Location;
import com.app.DeltaDrive.model.Vehicle;

import java.util.List;

public interface VehicleService {

    List<NearestVehicleDTO> findNearestVehicles(Location passengerLocation,Location destinationLocation);

    public Vehicle findById(Integer id);

    public Vehicle save(Vehicle vehicle);

    public Vehicle makeAvailable(Integer vehicleId);

    public Location findVehicleLocation(Integer vehicleId);

    public String findDriver(Integer vehicleId);
}
