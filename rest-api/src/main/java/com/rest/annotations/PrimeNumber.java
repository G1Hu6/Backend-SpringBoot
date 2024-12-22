package com.rest.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Constraint(
        validatedBy = {PrimeNumberValidator.class}
)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrimeNumber{
    String message() default "Number must be a valid Prime";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
