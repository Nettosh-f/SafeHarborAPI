package com.example.SafeHarborAPI2;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Safe Harbor API",
				version = "1.0",
				description = "REST API for managing ship information, security assessments, infractions, and harbor history",
				contact = @Contact(name = "Safe Harbor Admin", email = "admin@safeharbor.com"),
				license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")
		)
)
public class SafeHarborApi2Application {

	public static void main(String[] args) {
		SpringApplication.run(SafeHarborApi2Application.class, args);
	}
}