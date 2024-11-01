package com.app.DeltaDrive.service.impl;

import com.app.DeltaDrive.model.Location;
import com.app.DeltaDrive.model.Vehicle;
import com.app.DeltaDrive.service.CalculationService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService {

    private static final double EARTH_RADIUS = 6371000;


    public double calculateDistance(Location startLocation,Location endLocation) {

        double dLat = Math.toRadians((endLocation.latitude() - startLocation.latitude()));
        double dLong = Math.toRadians((endLocation.longitude() - startLocation.longitude()));

         var startLat = Math.toRadians(startLocation.latitude());
        var endLat = Math.toRadians(endLocation.latitude());

        double a = haversine(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversine(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }



    public double calculateTotalPrice(Vehicle vehicle,Location passLocation,Location destLocation) {


        double totalDistance= calculateTotalDistance(vehicle,passLocation,destLocation);

        double startPrice = extractPrice(vehicle.getStartPrice());
        double pricePerKM = extractPrice(vehicle.getPricePerKM());

        return startPrice + ((totalDistance / 1000) * pricePerKM);
    }

    public  double calculateTotalDistance(Vehicle vehicle,Location passLocation,Location destLocation){
        double distancePassengerDestination= calculateDistance(passLocation,destLocation);
        double distanceVehiclePassenger= calculateDistance(vehicle.getLocation(),passLocation);
        return distanceVehiclePassenger+distancePassengerDestination;
    }

    public double extractPrice(String priceString) {
        String numericString = priceString.replaceAll("[^\\d.]", "");
        return Double.parseDouble(numericString);
    }

    double haversine(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
