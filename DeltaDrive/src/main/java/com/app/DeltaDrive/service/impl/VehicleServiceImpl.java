package com.app.DeltaDrive.service.impl;

import com.app.DeltaDrive.dto.NearestVehicleDTO;
import com.app.DeltaDrive.mapper.NearestVehiclesDTOMapper;
import com.app.DeltaDrive.model.Vehicle;
import com.app.DeltaDrive.repository.VehicleRepository;
import com.app.DeltaDrive.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    private final NearestVehiclesDTOMapper mapper;


    public List<NearestVehicleDTO> findNearestVehicles(Double passenegerLatitude, Double passengerLongitude,Double destinationLatitude,Double destinationLongitude) {
        List<Vehicle> nearestVehicles = vehicleRepository.findTenNearest(passenegerLatitude, passengerLongitude);

        String nee= new String("HELOOU");
        System.out.println(nee);
        System.out.println(nearestVehicles.size());



        return nearestVehicles.stream()
                .map(vehicle -> {
                    double distanceFromPassenger = calculateDistance(vehicle.getLatitude(), vehicle.getLongitude(), passenegerLatitude, passengerLongitude);
                    double distanceFromDestination= calculateDistance(passenegerLatitude,passengerLongitude,destinationLatitude,destinationLongitude);
                    double totalPrice = calculateTotalPrice(vehicle,distanceFromDestination);
                    return new NearestVehicleDTO(
                            vehicle.getBrand(),
                            distanceFromPassenger,
                            totalPrice,
                            vehicle.getAvailability().toString()
                    );
                })
                .collect(Collectors.toList());
    }

    private double calculateTotalPrice(Vehicle vehicle, Double distanceFromDestination) {
        double startPrice = extractPrice(vehicle.getStartPrice());
        double pricePerKM = extractPrice(vehicle.getPricePerKM());

        return startPrice + ((distanceFromDestination / 1000) * pricePerKM);
    }

    private double extractPrice(String priceString) {
        String numericString = priceString.replaceAll("[^\\d.]", "");
        return Double.parseDouble(numericString);
    }
    public double calculateDistance(Double startLatitude,Double startLongitude, Double endLatitude, Double endLongitude) {
        GeometryFactory geometryFactory = new GeometryFactory();

        Point startPoint = geometryFactory.createPoint(new Coordinate(startLongitude, startLatitude));
        Point endPoint = geometryFactory.createPoint(new Coordinate(endLongitude, endLatitude));

        return startPoint.distance(endPoint);
    }



}
