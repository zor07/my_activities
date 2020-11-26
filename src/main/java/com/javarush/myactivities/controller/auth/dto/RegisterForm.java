package com.javarush.myactivities.controller.auth.dto;

import com.javarush.myactivities.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter @Setter
public class RegisterForm {

    private String name;
    private String login;
    private String password;
    private String confirmedPassword;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(login)
                .name(name)
                .password(passwordEncoder.encode(password))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }

}
