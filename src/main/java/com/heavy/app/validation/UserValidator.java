package com.heavy.app.validation;

import com.heavy.app.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *  User: DimaL
 Date: 15.11.16
 Time: 16:27
 */
@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass){
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "requered.name", "Name is required.");
    }
}
