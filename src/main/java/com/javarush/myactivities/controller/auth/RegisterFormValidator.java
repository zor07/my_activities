package com.javarush.myactivities.controller.auth;


import com.javarush.myactivities.controller.auth.dto.RegisterForm;
import com.javarush.myactivities.repositories.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegisterFormValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public RegisterFormValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterForm registerForm = (RegisterForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "","Имя не может быть пустым");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "","Логин не может быть пустым");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "","Пароль не может быть пустым");

        if (registerForm.getName().length() < 2) {
            errors.rejectValue("name", "","Минимальная длина имени - 2 символа");
        }

        if (registerForm.getLogin().length() < 3) {
            errors.rejectValue("login", "","Минимальная длина логина - 3 символа");
        }

        if (userRepository.findByUsername(registerForm.getLogin()) != null) {
            errors.rejectValue("login", "","Пользователь с таким логином уже существует!");
        }

        if (registerForm.getPassword().length() < 5) {
            errors.rejectValue("password", "","Минимальная длина пароля - 5 символов!");
        }

        if (!registerForm.getConfirmedPassword().equals(registerForm.getPassword())) {
            errors.rejectValue("confirmedPassword", "","Введенные пароли не совпадают");
        }
    }
}
