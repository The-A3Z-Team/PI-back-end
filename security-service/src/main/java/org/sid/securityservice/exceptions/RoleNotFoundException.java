package org.sid.securityservice.exceptions;

import org.sid.securityservice.entities.Role;

public class RoleNotFoundException extends Exception{
    public RoleNotFoundException(String role) {
        super(role);
    }
}
