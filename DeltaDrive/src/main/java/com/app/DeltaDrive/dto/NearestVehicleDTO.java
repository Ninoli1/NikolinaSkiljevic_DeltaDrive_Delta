package com.app.DeltaDrive.dto;



public record NearestVehicleDTO(

        Integer id,

         String brand,

         Double distanceFromPassenger,

         Double distancePassengerDestination,

         Double totalPrice,

        String availability) {
}
