package org.sid.securityservice.mappers;

import org.sid.securityservice.dtos.RoleDTO;
import org.sid.securityservice.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface RoleMapper {
    RoleDTO fromRole(Role role);
    Role fromRoleDTO(RoleDTO roleDTO);
    Set<RoleDTO> toRoleDTOs(Set<Role> roles);
    Set<Role> toRoles(Set<RoleDTO> roleDTOs);

}
