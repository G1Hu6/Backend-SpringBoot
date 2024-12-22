package com.rest.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordConstraintsValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                // length is between 6 and 12
                new LengthRule(6,12),

                // contains at least one UpperCase.
                new CharacterRule(EnglishCharacterData.UpperCase,1),

                // contains at least one LowerCase.
                new CharacterRule(EnglishCharacterData.LowerCase,1),

                // contains at least one Special.
                new CharacterRule(EnglishCharacterData.Special,1),

                // contains at least one Digit.
                new CharacterRule(EnglishCharacterData.Digit,1),

                // contains at least one UpperCase.
                new WhitespaceRule()
        ));

        RuleResult result = validator.validate(new PasswordData(password));
        if(result.isValid()){
            return true;
        }
        List<String> messages = validator.getMessages(result);
        System.out.println(messages);
        String messageTemplate = messages.stream()
                .collect(Collectors.joining(","));
        constraintValidatorContext.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
