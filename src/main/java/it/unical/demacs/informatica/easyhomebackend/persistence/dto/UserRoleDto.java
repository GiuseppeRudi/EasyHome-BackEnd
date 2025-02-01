package it.unical.demacs.informatica.easyhomebackend.persistence.dto;

import it.unical.demacs.informatica.easyhomebackend.model.UserRole;

public class UserRoleDto {
    private String username;
    private UserRole role;

    public UserRoleDto(String username, UserRole role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserRole getNewRole() {
        return this.role;
    }
}
