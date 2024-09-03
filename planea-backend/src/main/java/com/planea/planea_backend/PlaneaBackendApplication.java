package com.planea.planea_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlaneaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaneaBackendApplication.class, args);
		System.out.println("Server running on port 8005");
	}

}
