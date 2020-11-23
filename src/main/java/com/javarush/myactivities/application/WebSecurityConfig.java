package com.javarush.myactivities.application;

import com.javarush.myactivities.entities.helpers.GrantedAuthorities;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("buzz")
                    .password(passwordEncoder.encode("123456"))
                    .authorities(GrantedAuthorities.USER.getAuthority())
                .and()
                .withUser("woody")
                    .password(passwordEncoder.encode("bullseye"))
                    .authorities(GrantedAuthorities.USER.getAuthority())
                .and().passwordEncoder(passwordEncoder);
    }
}
