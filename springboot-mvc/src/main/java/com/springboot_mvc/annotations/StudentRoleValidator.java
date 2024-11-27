package com.springboot_mvc.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class StudentRoleValidator implements ConstraintValidator<StudentRoleValidation, String> {

    @Override
    public boolean isValid(String assignedRole, ConstraintValidatorContext constraintValidatorContext) {
        List<String> list = List.of("ADMIN","USER");
        return list.contains(assignedRole);
    }
}
