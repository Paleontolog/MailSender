package com.mailsender.demo.registration.validation;

import com.mailsender.demo.registration.service.UserService;
import com.mailsender.demo.web.dto.WebUser;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private EmailValidator emailValidator = EmailValidator.getInstance();
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == WebUser.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        WebUser user = (WebUser) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.null");

        if(!emailValidator.isValid(user.getUsername())) {
            errors.rejectValue("username", "username.incorrect");
        }
        if (userService.findUserByEmail(user.getUsername()) != null) {
            errors.rejectValue("username", "username.exist");
        }
    }
}

