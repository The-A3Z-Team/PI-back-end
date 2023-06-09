package org.sid.securityservice.mappers;

import org.sid.securityservice.dtos.RoleDTO;
import org.sid.securityservice.entities.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoleMapperImpl implements RoleMapper{
    public RoleDTO fromRole(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(Long.valueOf(role.getId()));
        roleDTO.setName(role.getName().toString());
        return roleDTO;
    }

    public Role fromRoleDTO(RoleDTO roleDTO){
        Role role=new Role();
        BeanUtils.copyProperties(roleDTO,role);
        return role;
    }
}
