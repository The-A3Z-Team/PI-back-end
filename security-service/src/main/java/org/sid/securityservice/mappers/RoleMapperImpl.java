package org.sid.securityservice.mappers;

import org.sid.securityservice.dtos.RoleDTO;
import org.sid.securityservice.entities.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDTO fromRole(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(Long.valueOf(role.getId()));
        roleDTO.setName(role.getName().toString());
        return roleDTO;
    }

    @Override
    public Role fromRoleDTO(RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);
        return role;
    }

    @Override
    public Set<RoleDTO> toRoleDTOs(Set<Role> roles) {
        return roles.stream()
                .map(this::fromRole)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Role> toRoles(Set<RoleDTO> roleDTOs) {
        return roleDTOs.stream()
                .map(this::fromRoleDTO)
                .collect(Collectors.toSet());
    }
}
