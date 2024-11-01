package com.app.DeltaDrive.repository;

import com.app.DeltaDrive.model.Location;
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
        ORDER BY ST_Distance(
            ST_Transform(ST_SetSRID(ST_MakePoint(v.location.longitude, v.location.latitude), 4326), 3857),
            ST_Transform(ST_SetSRID(ST_MakePoint(:#{#location.longitude}, :#{#location.latitude}), 4326), 3857)
        )
        LIMIT 10
        """)

    List<Vehicle> findTenNearest(@Param("location") Location location);
}

