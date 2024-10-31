package com.app.DeltaDrive.service;

import com.app.DeltaDrive.model.Location;
import com.app.DeltaDrive.model.Vehicle;

public interface CalculationService {

    public double calculateDistance(Location startLocation, Location endLocation) ;

    public double calculateTotalPrice(Vehicle vehicle,Location passLocation,Location destLocation);

    public double extractPrice(String priceString) ;

    public  double calculateTotalDistance(Vehicle vehicle,Location passLocation,Location destLocation);
}
