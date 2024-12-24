package com.mapping.services;

import com.mapping.entities.DepartmentEntity;
import com.mapping.entities.EmployeeEntity;
import com.mapping.repositories.DepartmentRepository;
import com.mapping.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public DepartmentEntity getDepartmentById(Long id){
        return departmentRepository.findById(id).orElse(null);
    }

    public DepartmentEntity insertDepartment(DepartmentEntity toSaveDepartment){
        return departmentRepository.save(toSaveDepartment);
    }

    public DepartmentEntity assignManagerToDepartment(Long employeeId, Long departmentID){
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentID);

        return departmentEntity.flatMap(
                department -> employeeEntity.map(employee -> {
                    department.setManager(employee);
                    return departmentRepository.save(department);
                })
        ).orElse(null);
    }

    public DepartmentEntity getAssignedDepartmentOfManager(Long employeeId){
        /*
        // Here in first way two api request are take place (less optimized)
        Optional<EmployeeEntity> selectedEmployee = employeeRepository.findById(employeeId);
        return selectedEmployee.map(EmployeeEntity::getManagedDepartment).orElse(null);
        */

        return departmentRepository.findByManager(EmployeeEntity.builder().id(employeeId).build());
    }

}
