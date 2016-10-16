package org.mservice.user.validator;

import org.mservice.user.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by oscarimac122 on 15/10/16.
 */
@Component
public class UserValidator implements Validator{

    @Override
    public boolean supports(Class<?> targetUserClass) {
        return User.class.isAssignableFrom(targetUserClass);
    }

    @Override
    public void validate(Object targetUser, Errors errors) {
        User user = (User) targetUser;
        if (user.getPassword().trim().equals("")) {
            errors.rejectValue("password", "Password is missing");
        }
    }
}
