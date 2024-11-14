package com.springcore.declaring_beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

// We can declare the Beans using two ways
// (1) Using Stereotype Annotation :- @Component, @Service, @Repository, @Controller
//                                    @Component is parent Interface for all other stereotype annotations.

//@Component
public class Mango {

    public void says(){
        System.out.println("Inside Mango class");
        System.out.println("I am the King of all fruits");
    }

    // Bean Lifecycle methods
    @PostConstruct
    public void runningBeforeUsingMango(){
        System.out.println("Running before using Mango bean");
    }

    @PreDestroy
    public void runningDestroyMango(){
        System.out.println("Before Destroying Mango bean");
    }
}
