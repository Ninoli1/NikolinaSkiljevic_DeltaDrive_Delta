package com.app.DeltaDrive.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record UserDTO(

        @NotBlank(message = "First Name should not be blank!")
        String firstName,

        @NotBlank(message = "Last Name should not be blank!")
        String lastName,

        @NotBlank(message = "Email  should not be blank!")
        @Email
        String email,

        @NotBlank(message = "Password should not be blank!")
        String password,

        @Pattern(regexp = "^\\d{2}\\.\\d{2}\\.\\d{4}\\.$", message = "Please enter the date in the format dd.MM.yyyy.")
        @NotBlank(message = "Birthday should not be blank!")
        String birthday,

        @NotBlank(message = "Role should not be blank!")
        String role
     ) {
}
