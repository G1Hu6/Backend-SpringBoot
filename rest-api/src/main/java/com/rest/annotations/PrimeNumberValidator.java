package com.rest.annotations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumber, Integer> {

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return isPrime(integer);
    }

    private boolean isPrime(Integer number){
        if(number <= 1){
            return false;
        }
        for(int i = 2; i*i <= number; i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
}
