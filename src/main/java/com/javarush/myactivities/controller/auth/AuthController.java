package com.javarush.myactivities.controller.auth;

import com.javarush.myactivities.controller.auth.dto.RegisterForm;
import com.javarush.myactivities.repositories.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RegisterFormValidator registerFormValidator;

    @Autowired
    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          RegisterFormValidator registerFormValidator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.registerFormValidator = registerFormValidator;
    }

    @RequestMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "auth/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(RegisterForm registerForm, Errors errors) {
        registerFormValidator.validate(registerForm, errors);

        if (errors.hasErrors()) {
            return "auth/register";
        }

        userRepository.save(registerForm.toUser(passwordEncoder));
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String loginForm() {
        return "auth/login";
    }
}
