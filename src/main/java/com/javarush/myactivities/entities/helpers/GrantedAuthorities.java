package com.javarush.myactivities.entities.helpers;

import org.springframework.security.core.GrantedAuthority;

public enum GrantedAuthorities implements GrantedAuthority {

    USER("user");

    private final String authority;

    GrantedAuthorities(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
