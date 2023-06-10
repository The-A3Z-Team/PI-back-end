package org.sid.securityservice.mappers;

import org.sid.securityservice.dtos.UserResponseDTO;
import org.sid.securityservice.entities.User;

import java.util.List;

public interface UserResponseDTOMapper {
    UserResponseDTO fromUser(User user);
    User fromUserResponseDTO(UserResponseDTO userResponseDTO);
    List<UserResponseDTO> toUserResponseDTOs(List<User> users);
}
