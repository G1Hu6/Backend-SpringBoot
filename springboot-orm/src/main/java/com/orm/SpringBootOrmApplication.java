package com.orm;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Product Api",description = "Product Api using Spring Boot", version = "1.0"))
public class SpringBootOrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOrmApplication.class, args);
	}

}
