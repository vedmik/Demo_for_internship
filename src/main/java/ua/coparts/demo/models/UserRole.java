package ua.coparts.demo.models;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    USER,
    ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
