package com.app.DeltaDrive;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.app.DeltaDrive.model")
@SpringBootApplication
@ComponentScan
@EnableJpaRepositories(basePackages = "com.app.DeltaDrive.repository")
@OpenAPIDefinition(security = { @SecurityRequirement(name = "jwt"), @SecurityRequirement(name = "basic") })
public class DeltaDriveApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeltaDriveApplication.class, args);
	}

}
