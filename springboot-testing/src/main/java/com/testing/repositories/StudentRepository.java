package com.testing.repositories;

import com.testing.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// All methods for CURD operation are defined by JpaRepository no need to
// handle by developer.
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
    List<StudentEntity> findByEmail(String email);
}
