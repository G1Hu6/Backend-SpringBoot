package com.springcore.declaring_beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class DeclarationUsingStereotypeAnnotationTestApp implements CommandLineRunner{

    @Autowired
    Mango mango;

    public void run(String[] args)throws Exception{
        mango.says();
    }

    public void fun(){
        mango.says();
    }
    public static void main(String[] args) {
        SpringApplication.run(DeclarationUsingStereotypeAnnotationTestApp.class, args);
        //new DeclarationUsingStereotypeAnnotationTestApp().fun();  // Exception in thread "main" java.lang.NullPointerException
    }
}
