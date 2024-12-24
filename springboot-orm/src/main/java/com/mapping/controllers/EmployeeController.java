package com.mapping.controllers;

import com.mapping.entities.EmployeeEntity;
import com.mapping.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/{id}")
    public EmployeeEntity getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public EmployeeEntity insertEmployee(@RequestBody EmployeeEntity employeeEntity){
        return employeeService.insertEmployee(employeeEntity);
    }
}
