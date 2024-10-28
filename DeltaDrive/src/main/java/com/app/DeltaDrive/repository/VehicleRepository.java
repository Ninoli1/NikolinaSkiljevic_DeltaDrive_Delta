package com.app.DeltaDrive.repository;

import com.app.DeltaDrive.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {

    @Query(value = """
        SELECT v FROM Vehicle v
        WHERE v.availability = 'AVAILABLE'
        ORDER BY ST_Distance(ST_SetSRID(ST_MakePoint(v.longitude, v.latitude), 4326), 
                             ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326))
        LIMIT 10
        """)
    List<Vehicle> findTenNearest(@Param("latitude") double latitude, @Param("longitude") double longitude);
}

