package com.app.DeltaDrive.service.impl;

import com.app.DeltaDrive.dto.RateDTO;
import com.app.DeltaDrive.mapper.RateDTOMapper;
import com.app.DeltaDrive.model.Rate;
import com.app.DeltaDrive.repository.RateRepository;
import com.app.DeltaDrive.service.AuthenticationService;
import com.app.DeltaDrive.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;
    private final AuthenticationService authService;
    private final RateDTOMapper rateDTOMapper;
    private final AuthenticationService authenticationService;
    @Override
    public Rate create(RateDTO rateDTO) {
        try{
            Rate newRate= rateDTOMapper.mapRateDTOToRate(rateDTO);
            newRate.setPassengerEmail(authenticationService.findLoggedInEmail());
          return  rateRepository.save(newRate);
        }catch (IllegalArgumentException | DataIntegrityViolationException e){
            throw new RuntimeException("Couldn't create a rate"+e.getMessage(),e);
        }
    }

    @Override
    public List<Rate> findMyRates() {
        return rateRepository.findByPassengerEmail(authService.findLoggedInEmail());
    }
}
