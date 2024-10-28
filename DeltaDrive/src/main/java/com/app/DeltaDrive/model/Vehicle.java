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

    private String firstName;

    private String lastName;

    private Double latitude;

    private Double longitude;

    private String startPrice;

    private String pricePerKM;

    @Enumerated(EnumType.STRING)
    private Availability availability;


    public Vehicle(String brand, String driverFirstName, String driverLastName, Double latitude, Double longitude, String startPrice, String pricePerKm, Availability available) {
        this.brand = brand;
        this.firstName = driverFirstName;
        this.lastName = driverLastName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startPrice = startPrice;
        this.pricePerKM = pricePerKm;
        this.availability = available;
    }
}
