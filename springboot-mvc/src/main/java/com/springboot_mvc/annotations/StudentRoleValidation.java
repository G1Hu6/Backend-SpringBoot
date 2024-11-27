package com.springboot_mvc.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Creating custom validation annotation for field role in StudentDTO

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {StudentRoleValidator.class}
)
public @interface StudentRoleValidation {

    String message() default "Role must be ADMIN or USER";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
