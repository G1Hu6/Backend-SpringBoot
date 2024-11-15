package com.dependency.injection;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "deploy.core.env",havingValue = "dev")
public class DevelopmentDB implements DB{

    String getDevelopmentData(){
        return "Development database Api";
    }

    @Override
    public String getData() {
        return "Development database Api Loosely coupled";
    }
}
