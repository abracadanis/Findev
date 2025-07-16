package com.example.demo.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.model.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    USER(Set.of(USER_READ, PROJECT_READ, PROJECT_WRITE)),
    ADMIN(Set.of(USER_READ, USER_READ_ALL, USER_WRITE, PROJECT_READ, PROJECT_WRITE, ApplicationUserPermission.ADMIN));

    private final Set<ApplicationUserPermission>permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissons()))
                .collect(Collectors.toSet());
    }
}
