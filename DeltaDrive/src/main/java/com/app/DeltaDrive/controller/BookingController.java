package com.app.DeltaDrive.controller;


import com.app.DeltaDrive.dto.BookingRequestDTO;
import com.app.DeltaDrive.model.Location;
import com.app.DeltaDrive.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/request")
    public ResponseEntity<String> bookAVehicle(@RequestBody BookingRequestDTO request){
        return ResponseEntity.ok(bookingService.bookAVehicle(request));

    }
}
