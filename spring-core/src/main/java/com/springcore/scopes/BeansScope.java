package com.springcore.scopes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BeansScope implements CommandLineRunner {

    @Autowired
    King king1;

    @Autowired
    King king2;

    @Override
    public void run(String... args) throws Exception {
        king1.working();
        king2.working();

        System.out.println(king1.hashCode());
        System.out.println(king2.hashCode());
    }

    public static void main(String[] args){

        SpringApplication.run(BeansScope.class, args);
        System.out.println("SpringBoot Introduction...");
    }

}