package com.app.DeltaDrive.model;


import com.app.DeltaDrive.model.enums.Availability;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;

    private String driverFirstName;

    private String driverLastName;

    @Embedded
    private Location location;

    private String startPrice;

    private String pricePerKM;

    @Enumerated(EnumType.STRING)
    private Availability availability;


    public Vehicle(String brand, String driverFirstName, String driverLastName, Location location,  String startPrice, String pricePerKm, Availability available) {
        this.brand = brand;
        this.driverFirstName = driverFirstName;
        this.driverLastName = driverLastName;
        this.location=location;
        this.startPrice = startPrice;
        this.pricePerKM = pricePerKm;
        this.availability = available;
    }
}
