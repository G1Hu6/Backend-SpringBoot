package com.example.bakery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CakeBaker {

    @Autowired
    Frosting frosting;

    @Autowired
    Syrup syrup;

    public void bakeCake(){
        System.out.println("Syrup Type :" + syrup.getSyrupType());
        System.out.println("Frosting Type :" +frosting.getFrostingType());
    }
}
