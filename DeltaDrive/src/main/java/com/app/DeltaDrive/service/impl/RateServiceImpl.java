package com.app.DeltaDrive.service.impl;

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
    @Override
    public Rate create(Rate rate) {
        try{
          return  rateRepository.save(rate);
        }catch (IllegalArgumentException | DataIntegrityViolationException e){
            throw new RuntimeException("Couldn't create a rate"+e.getMessage(),e);
        }
    }

    @Override
    public List<Rate> findMyRates() {
        return rateRepository.findByPassengerEmail(authService.findLoggedInEmail());
    }
}
