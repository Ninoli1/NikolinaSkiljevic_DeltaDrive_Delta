package com.app.DeltaDrive.service.impl;

import com.app.DeltaDrive.model.Location;
import com.app.DeltaDrive.model.Vehicle;
import com.app.DeltaDrive.service.CalculationService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService {

    private final GeometryFactory geometryFactory = new GeometryFactory();

    public double calculateDistance(Location startLocation, Location endLocation) {
        Point startPoint = geometryFactory.createPoint(new Coordinate(startLocation.longitude(), startLocation.latitude()));
        Point endPoint = geometryFactory.createPoint(new Coordinate(endLocation.longitude(), endLocation.latitude()));

        return startPoint.distance(endPoint);
    }

    public double calculateTotalPrice(Vehicle vehicle, Double distanceFromDestination) {
        double startPrice = extractPrice(vehicle.getStartPrice());
        double pricePerKM = extractPrice(vehicle.getPricePerKM());

        return startPrice + ((distanceFromDestination / 1000) * pricePerKM);
    }

    public double extractPrice(String priceString) {
        String numericString = priceString.replaceAll("[^\\d.]", "");
        return Double.parseDouble(numericString);
    }
}
