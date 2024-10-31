package com.app.DeltaDrive.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(1)
    @Max(5)
    private Integer value;

    private String comment;

    private String passengerEmail;
    private String vehicleId;

}
