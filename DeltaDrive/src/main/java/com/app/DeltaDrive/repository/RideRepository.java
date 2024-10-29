package com.app.DeltaDrive.repository;


import com.app.DeltaDrive.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride,Integer> {
}
