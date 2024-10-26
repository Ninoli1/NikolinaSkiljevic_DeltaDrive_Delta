package com.app.DeltaDrive.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;



public record LoginDTO(
        @NotBlank(message = "Email  should not be blank!")
        @Email
        String email,

        @NotBlank(message = "Password should not be blank!")
        String password) {
}
