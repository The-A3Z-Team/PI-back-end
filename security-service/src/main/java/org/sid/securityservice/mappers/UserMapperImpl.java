package org.sid.securityservice.mappers;

import org.sid.securityservice.dtos.RoleDTO;
import org.sid.securityservice.dtos.UserDTO;
import org.sid.securityservice.entities.Role;
import org.sid.securityservice.entities.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserMapperImpl implements UserMapper{
    private RoleMapperImpl roleMapper;

    public UserMapperImpl(RoleMapperImpl roleMapper) {
        this.roleMapper = roleMapper;
    }

    public UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        // Map other properties
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());

        // Map roles
        Set<RoleDTO> roleDTOList = user.getRoles().stream()
                .map(roleMapper::fromRole)
                .collect(Collectors.toSet());
        userDTO.setRoleDTOList(roleDTOList);

        return userDTO;
    }

    public User fromUserDTO(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user, "roleDTOList");

        Set<Role> roles = userDTO.getRoleDTOList().stream()
                .map(roleMapper::fromRoleDTO)
                .collect(Collectors.toSet());

        user.setRoles(roles);
        return user;
    }

    public List<UserDTO> toUserDTOs(List<User> users) {
        return users.stream()
                .map(this::fromUser)
                .collect(Collectors.toList());
    }
}
