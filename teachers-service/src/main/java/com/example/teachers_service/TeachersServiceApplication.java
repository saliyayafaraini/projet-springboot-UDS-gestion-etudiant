package com.example.teachers_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootAphplication
@EnableDiscoveryClient
public class TeachersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeachersServiceApplication.class, args);
	}

}
