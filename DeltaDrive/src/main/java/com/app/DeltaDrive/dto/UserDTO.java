package com.app.DeltaDrive.dto;

import lombok.Data;
import lombok.Getter;


public record UserDTO(
        String firstName,
        String lastName,
        String email,
        String password,
        String birthday,
        String role
     ) {
}
