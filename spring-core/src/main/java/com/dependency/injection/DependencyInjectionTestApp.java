package com.dependency.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DependencyInjectionTestApp implements CommandLineRunner {

    @Autowired
    DatabaseService databaseService;

    public static void main(String[] args) {

        SpringApplication.run(DependencyInjectionTestApp.class,args);

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(databaseService.getData());
    }
}
