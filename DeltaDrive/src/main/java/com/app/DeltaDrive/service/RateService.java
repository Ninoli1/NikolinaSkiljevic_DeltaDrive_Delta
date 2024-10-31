package com.app.DeltaDrive.service;

import com.app.DeltaDrive.model.Rate;

import java.util.List;

public interface RateService {

    public Rate create(Rate rate);

    public List<Rate> findMyRates();
}
