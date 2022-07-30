package me.hung.auth.entity;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEntity implements GrantedAuthority {

    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String role;

    RoleEntity(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
