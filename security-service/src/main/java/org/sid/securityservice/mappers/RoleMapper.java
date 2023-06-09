package org.sid.securityservice.mappers;

import org.sid.securityservice.dtos.RoleDTO;
import org.sid.securityservice.entities.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleMapper {
    public RoleDTO fromRole(Role role);
    public Role fromRoleDTO(RoleDTO roleDTO);
}
