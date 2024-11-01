package com.app.DeltaDrive.mapper;

import com.app.DeltaDrive.dto.RateDTO;
import com.app.DeltaDrive.dto.RideDTO;
import com.app.DeltaDrive.model.Rate;
import com.app.DeltaDrive.model.Ride;
import com.app.DeltaDrive.model.enums.RideStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class RateDTOMapper {

    public Rate mapRateDTOToRate(RateDTO rateDTO){

        Rate rate = new Rate(rateDTO.value(), rateDTO.comment(), rateDTO.vehicleId());
        return rate;
    }
}
