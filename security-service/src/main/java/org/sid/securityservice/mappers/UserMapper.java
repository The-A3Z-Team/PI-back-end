package org.sid.securityservice.mappers;

import org.sid.securityservice.dtos.UserDTO;
import org.sid.securityservice.entities.User;

import java.util.List;

public interface UserMapper{
    public UserDTO fromUser(User user);
    public User fromUserDTO(UserDTO userDTO);
    public List<UserDTO> toUserDTOs(List<User> users);
}
