package com.app.DeltaDrive.repository;


import com.app.DeltaDrive.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate,Integer> {

    List<Rate> findByPassengerEmail(String email);
}
