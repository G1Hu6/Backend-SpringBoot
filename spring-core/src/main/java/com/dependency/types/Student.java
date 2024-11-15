package com.dependency.types;

import org.springframework.stereotype.Component;

@Component
public class Student {

    private HomeAddress homeAddress; // Dependency is Injected through Constructor Injection

    public Student(HomeAddress homeAddress){
        System.out.println("Injected through Constructor");
        this.homeAddress = homeAddress;
    }

    public String getHomeAddress(){
        return this.homeAddress.getAddress();
    }
}
