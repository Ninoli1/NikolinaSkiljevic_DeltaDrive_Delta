package com.app.DeltaDrive.repository;


import com.app.DeltaDrive.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride,Integer> {
    List<Ride> findByPassengerEmail(String passengerEmail);
}
