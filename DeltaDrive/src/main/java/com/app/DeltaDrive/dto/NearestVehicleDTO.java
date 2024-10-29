package com.app.DeltaDrive.dto;



public record NearestVehicleDTO(

        Integer id,

         String brand,

         Double distanceFromPassenger,

         Double totalPrice,

        String availability) {
}
