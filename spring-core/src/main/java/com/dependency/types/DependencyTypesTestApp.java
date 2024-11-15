package com.dependency.types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


    @SpringBootApplication
    public class DependencyTypesTestApp implements CommandLineRunner {

        @Autowired
        Student student;

        public static void main(String[] args) {

            SpringApplication.run(DependencyTypesTestApp.class, args);
            System.out.println("SpringBoot Introduction...");
        }

        @Override
        public void run(String... args) throws Exception {
            System.out.println(student.getHomeAddress());
        }
}
