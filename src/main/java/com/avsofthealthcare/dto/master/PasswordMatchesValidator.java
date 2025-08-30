package com.avsofthealthcare.dto.master; // or your validation package

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true; // nothing to validate

        BeanWrapperImpl wrapper = new BeanWrapperImpl(value);
        Object password = wrapper.getPropertyValue("password");
        Object confirm  = wrapper.getPropertyValue("confirmPassword");

        if (password == null || confirm == null) return false;

        boolean matches = password.equals(confirm);
        if (!matches) {
            // attach error to the confirmPassword field
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Passwords do not match")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }
        return matches;
    }
}
