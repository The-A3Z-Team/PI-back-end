package org.sid.securityservice.mappers;

import org.sid.securityservice.dtos.UserDTO;
import org.sid.securityservice.dtos.UserResponseDTO;
import org.sid.securityservice.entities.User;

import java.util.List;

public interface UserDTOMapper {
    UserDTO fromUser(User user);
    User fromUserDTO(UserDTO userDTO);
}
