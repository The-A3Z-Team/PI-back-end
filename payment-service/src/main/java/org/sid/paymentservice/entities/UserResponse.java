package org.sid.paymentservice.entities;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String password;
}
