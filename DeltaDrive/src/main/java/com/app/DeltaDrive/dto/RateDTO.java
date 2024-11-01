package com.app.DeltaDrive.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record RateDTO(@Min(value = 1,message = "Value must be from 1 to 5")
                      @Max(value = 5,message = "Value must be from 1 to 5")
                       Integer value,

                        String comment,

                        Integer vehicleId) {
}
