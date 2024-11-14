package com.springcore.beans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeclaringObjectTraditionalWay {
    public static void main(String[] args){
        SpringApplication.run(DeclaringObjectTraditionalWay.class,
                args);
        SpaceShip agni = new SpaceShip();
        agni.launch();
        System.out.println("SpaceShip Code : " + agni.hashCode());
    }
}
