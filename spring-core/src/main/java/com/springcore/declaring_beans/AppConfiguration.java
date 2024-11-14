package com.springcore.declaring_beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// We can declare the Beans using two ways
// (2) Explicit Bean declaration in Configuration class :- @Configuration and @Bean Annotation is used

@Configuration
public class AppConfiguration {

    // static factory method which return object of Mango class
    @Bean
    static Mango getMango(){
        System.out.println("Inside AppConfiguration");
        return new Mango();
    }
}
