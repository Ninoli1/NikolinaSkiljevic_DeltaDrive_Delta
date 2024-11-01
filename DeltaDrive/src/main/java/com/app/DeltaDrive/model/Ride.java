package com.app.DeltaDrive.model;

import com.app.DeltaDrive.model.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String passengerEmail;




    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "dest_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "dest_longitude"))
    })
    private Location destinationLocation;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "pass_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "pass_longitude"))
    })
    private Location passengerLocation;

    private Double totalPrice;

    private Double totalDistance;

    private Integer vehicleId;

    @Enumerated(EnumType.STRING)
    private RideStatus status;
}
