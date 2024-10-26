package com.app.DeltaDrive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan(basePackages = "com.app.DeltaDrive.model")
@SpringBootApplication
@ComponentScan
public class DeltaDriveApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeltaDriveApplication.class, args);
	}

}
