package com.springcore;

import com.dependency.types.HomeAddress;
import com.dependency.types.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringCoreApplication implements CommandLineRunner {

	@Autowired
	public static void main(String[] args) {

		SpringApplication.run(SpringCoreApplication.class, args);
		System.out.println("SpringBoot Introduction...");
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
