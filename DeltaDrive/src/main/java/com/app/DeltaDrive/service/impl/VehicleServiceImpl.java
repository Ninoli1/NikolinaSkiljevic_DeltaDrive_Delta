package com.app.DeltaDrive.service.impl;

import com.app.DeltaDrive.dto.NearestVehicleDTO;
import com.app.DeltaDrive.mapper.NearestVehiclesDTOMapper;
import com.app.DeltaDrive.model.Location;
import com.app.DeltaDrive.model.Vehicle;
import com.app.DeltaDrive.model.enums.Availability;
import com.app.DeltaDrive.repository.VehicleRepository;
import com.app.DeltaDrive.service.CalculationService;
import com.app.DeltaDrive.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    private final NearestVehiclesDTOMapper mapper;

    private final CalculationService calculationService;


    public List<NearestVehicleDTO> findNearestVehicles(Location passengerLocation,Location destinationLocation) {
        List<Vehicle> nearestVehicles = vehicleRepository.findTenNearest(passengerLocation);

        return nearestVehicles.stream()
                .map(vehicle -> {
                    double distanceVehiclePassenger = calculationService.calculateDistance(vehicle.getLocation(), passengerLocation);
                    double distancePassengerDestination= calculationService.calculateDistance(passengerLocation,destinationLocation);
                    double totalPrice = calculationService.calculateTotalPrice(vehicle,passengerLocation,destinationLocation);

                    return new NearestVehicleDTO(
                            vehicle.getId(),
                            vehicle.getBrand(),
                            distanceVehiclePassenger,
                            distancePassengerDestination,
                            totalPrice,
                            vehicle.getAvailability().toString()
                    );
                })
                .collect(Collectors.toList());
    }

    public Vehicle findById(Integer id){
        return vehicleRepository.findById(id).orElseThrow(()->new NoSuchElementException("Vehicle with id"+id+"not found!"));
    }

    public Vehicle save(Vehicle vehicle) {
        try {
            return vehicleRepository.save(vehicle);
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            throw new RuntimeException("Error saving vehicle: " + e.getMessage(), e);
        }
    }

    public Vehicle makeAvailable(Integer vehicleId){
        Vehicle oldVehicle= findById(vehicleId);
        oldVehicle.setAvailability(Availability.AVAILABLE);
        return save(oldVehicle);
    }

    @Override
    public Location findVehicleLocation(Integer vehicleId) {
        Vehicle vehicle= findById(vehicleId);
        return vehicle.getLocation();
    }

    @Override
    public String findDriver(Integer vehicleId) {
        Vehicle vehicle= findById(vehicleId);
        return vehicle.getFirstName()+vehicle.getLastName();
    }

}
