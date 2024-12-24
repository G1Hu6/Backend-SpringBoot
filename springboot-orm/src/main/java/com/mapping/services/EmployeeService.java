package com.mapping.services;

import com.mapping.entities.EmployeeEntity;
import com.mapping.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeEntity getEmployeeById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public EmployeeEntity insertEmployee(EmployeeEntity toSaveEmployee){
        return employeeRepository.save(toSaveEmployee);
    }
}
