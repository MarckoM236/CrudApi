package com.marco.crudapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	info = @Info(
			title = "Products API",
			version = "1.0",
			description = "API documentation for managing products with authentication JWT"
	)
)

@SpringBootApplication
public class CrudapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudapiApplication.class, args);
	}

}
