package com.app.DeltaDrive.controller;

import com.app.DeltaDrive.model.Rate;
import com.app.DeltaDrive.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rates")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    @PostMapping("/create")
    public ResponseEntity<Rate> create(@RequestBody Rate rate){
        return ResponseEntity.ok(rateService.create(rate));
    }

    @GetMapping("/myRates")
    public ResponseEntity<List<Rate>> findMyRates(){
        return ResponseEntity.ok(rateService.findMyRates());
    }

}
