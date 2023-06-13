package org.sid.securityservice.dtos;

import java.util.List;

public class AuthResponse {
    private String accessToken;
    private List<String> roles;
    private UserResponseDTO user;

    public AuthResponse(String accessToken, List<String> roles, UserResponseDTO user) {
        this.accessToken = accessToken;
        this.roles = roles;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }
}

