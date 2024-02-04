package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.nitconf, com.example.controller"})
public class NitconfApplication {

	public static void main(String[] args) {
		SpringApplication.run(NitconfApplication.class, args);
	}

}
