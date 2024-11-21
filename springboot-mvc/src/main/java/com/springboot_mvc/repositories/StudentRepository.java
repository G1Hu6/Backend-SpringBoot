package com.springboot_mvc.repositories;

import com.springboot_mvc.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// All methods for CURD operation are defined by JpaRepository no need to
// handle by developer.
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {

}
