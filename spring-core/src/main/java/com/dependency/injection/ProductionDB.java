package com.dependency.injection;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
@ConditionalOnProperty(name = "deploy.core.env",havingValue = "pro")
public class ProductionDB implements DB{

    String getProductionData(){
        return "Production database Api";
    }

    @Override
    public String getData() {
        return "Production database Api Loosely coupled";
    }
}
