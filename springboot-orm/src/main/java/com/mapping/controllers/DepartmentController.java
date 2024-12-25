package com.mapping.controllers;

import com.mapping.entities.DepartmentEntity;
import com.mapping.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(path = "/{id}")
    public DepartmentEntity getDepartmentById(@PathVariable Long id){
        return departmentService.getDepartmentById(id);
    }

    @GetMapping(path = "/assignedDepartmentOfManager/{employeeId}")
    public DepartmentEntity getAssignedDepartmentOfManager(@PathVariable Long employeeId){
        return departmentService.getAssignedDepartmentOfManager(employeeId);
    }

    @PostMapping
    public DepartmentEntity insertDepartment(@RequestBody DepartmentEntity departmentEntity){
        return departmentService.insertDepartment(departmentEntity);
    }

    @PutMapping("/{departmentId}/manager/{employeeId}")
    public DepartmentEntity assignManagerToDepartment(@PathVariable Long departmentId, @PathVariable Long employeeId){
        return departmentService.assignManagerToDepartment(employeeId, departmentId);
    }

    @PutMapping("/{departmentId}/worker/{employeeId}")
    public DepartmentEntity assignWorkerToDepartment(@PathVariable Long departmentId, @PathVariable Long employeeId){
        return departmentService.assignWorkerToDepartment(employeeId, departmentId);
    }

    @PutMapping("/{departmentId}/freelancer/{employeeId}")
    public DepartmentEntity assignFreelancerToDepartment(@PathVariable Long departmentId, @PathVariable Long employeeId){
        return departmentService.assignFreelancerToDepartment(employeeId, departmentId);
    }
}
