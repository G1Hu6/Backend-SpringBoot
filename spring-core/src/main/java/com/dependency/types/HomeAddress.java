package com.dependency.types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomeAddress {

    private String address = "Narhe Pune";

    public String getAddress(){
        return this.address;
    }
}
