package com.app.DeltaDrive.configuration;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name="bearerAuth",
                description = "Jwt auth ",
                scheme="bearer",
                type= SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                in= SecuritySchemeIn.HEADER)
public class OpenApiConfiguration {
}