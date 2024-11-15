package com.dependency.injection;
// Service class

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
    Here by changing the private class variables manually makes
    our code tightly coupled.

    So, we have to create interface DB and ProductionDB and DevelopmentDB
    must be implements DB.
*/

@Service
public class DatabaseService {

      @Autowired
      private DB database;  // For loose coupling

/*
      @Autowired
      private DevelopmentDB devDB;

      @Autowired
      private ProductionDB proDB;
*/

      String getData(){
          //return proDB.getProductionData();  // Tightly coupled

          // Here Ambiguity arises so we have to use @Primary annotation for preference.
          //Consider marking one of the beans as @Primary, updating the consumer to
          // accept multiple beans, or using @Qualifier to identify the bean that should be consumed.
          return database.getData();
      }
}
