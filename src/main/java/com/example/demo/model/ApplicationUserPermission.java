package com.example.demo.model;

public enum ApplicationUserPermission {
    USER_READ("user:read"),
    USER_READ_ALL("user:readAll"),
    USER_WRITE("user:write"),
    ADMIN("admin"),
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
