package com.backend.calculator.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication denotes a configuration class that declares one or more @Bean methods
// and also triggers auto-configuration and component scanning. It's a convenience annotation
// that is equivalent to declaring @Configuration, @EnableAutoConfiguration, and @ComponentScan.
@SpringBootApplication
public class CalculatorApplication {

	// Main method that serves as the entry point for the Spring Boot application.
	public static void main(String[] args) {
		// SpringApplication.run() starts the application by creating the Spring application context.
		// The CalculatorApplication.class is passed as an argument to set up the configuration class,
		// and args are passed to provide command-line arguments if any.
		SpringApplication.run(CalculatorApplication.class, args);
	}

}
