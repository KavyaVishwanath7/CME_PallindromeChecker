package com.cme.palindrome.common;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PalindromeInputValidator implements ConstraintValidator<ValidPalindromeInput,
        String> {
    private String errorMessage;

    @Override
    public void initialize(ValidPalindromeInput constraintAnnotation) {
        errorMessage = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValidInput = false;

        // checks for null,empty, numbers and spaces in the input 'tetValue'
        isValidInput= input != null &&
                !input.isEmpty() &&
                !input.matches(".*\\d.*") &&
                !input.matches(".*\\s.*");

        /* if the above check is true the initialize constraintValidator message
         to the default message specified in the interface-ValidPalindromeInput */
        if(isValidInput){
            constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
        }
        return isValidInput;
    }
}
