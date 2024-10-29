package com.app.DeltaDrive.service;

import com.app.DeltaDrive.model.Location;
import com.app.DeltaDrive.model.Vehicle;

public interface CalculationService {

    public double calculateDistance(Location startLocation, Location endLocation) ;

    public double calculateTotalPrice(Vehicle vehicle, Double distanceFromDestination);

    public double extractPrice(String priceString) ;
}
