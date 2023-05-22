package ru.asayke.houses.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.asayke.houses.model.User;
import ru.asayke.houses.service.UserService;

@Component
@AllArgsConstructor
public class UserValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if(userService.findByUsername(user.getUsername()) != null)
            errors.rejectValue("username", "The username is already taken");

        if(user.getPassword() == null)
            errors.rejectValue("password", "Password can't be null");
    }
}