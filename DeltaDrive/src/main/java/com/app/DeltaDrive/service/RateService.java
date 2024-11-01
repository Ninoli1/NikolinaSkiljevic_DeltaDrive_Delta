package com.app.DeltaDrive.service;

import com.app.DeltaDrive.dto.RateDTO;
import com.app.DeltaDrive.model.Rate;

import java.util.List;

public interface RateService {

    public Rate create(RateDTO rate);

    public List<Rate> findMyRates();
}
