package com.app.DeltaDrive.service;

import com.app.DeltaDrive.dto.BookingRequestDTO;
import com.app.DeltaDrive.model.Location;

public interface BookingService {
    public String bookAVehicle(BookingRequestDTO request);
}
