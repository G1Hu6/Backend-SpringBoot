package com.springboot_mvc.exceptions;

// Creating custom exception class
public class ResponseNotFoundException extends RuntimeException{

    public ResponseNotFoundException(String message){
        super(message);
    }
}
