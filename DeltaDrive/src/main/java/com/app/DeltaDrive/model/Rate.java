package com.app.DeltaDrive.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(value = 1,message = "Value must be from 1 to 5")
    @Max(value = 5,message = "Value must be from 1 to 5")
    private Integer value;

    private String comment;

    private String passengerEmail;
    private Integer vehicleId;

    public Rate(Integer value, String comment, Integer vehicleId) {
        this.value = value;
        this.comment = comment;
        this.vehicleId = vehicleId;
    }
}
