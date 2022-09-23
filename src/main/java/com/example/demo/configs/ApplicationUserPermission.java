package com.example.demo.configs;

public enum ApplicationUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    PROJECT_READ("project:read"),
    PROJECT_WRITE("project:write");


    private final String permissons;

    ApplicationUserPermission(String permissons){
        this.permissons = permissons;
    }

    public String getPermissons() {
        return permissons;
    }
}
