package com.testing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringbootTestingApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootTestingApplication.class, args);
		log.info("SpringBoot-Testing project running...");
	}

}
