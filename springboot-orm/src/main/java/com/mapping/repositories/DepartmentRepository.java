package com.mapping.repositories;

import com.mapping.entities.DepartmentEntity;
import com.mapping.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
    DepartmentEntity findByManager(EmployeeEntity employee);
}
