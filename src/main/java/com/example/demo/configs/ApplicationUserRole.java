package com.example.demo.configs;

import com.google.common.collect.Sets;
import java.util.Set;

import static com.example.demo.configs.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(USER_READ, PROJECT_READ, PROJECT_WRITE)),
    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE, PROJECT_READ, PROJECT_WRITE));

    private final Set<ApplicationUserPermission>permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
