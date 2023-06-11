package org.sid.securityservice.mappers;

import org.sid.securityservice.dtos.UserDTO;
import org.sid.securityservice.entities.User;
import org.springframework.stereotype.Service;


@Service
public class UserDTOMapperImpl implements UserDTOMapper {
    @Override
    public UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setDateNaissance(user.getDateNaissance());
        userDTO.setGender(user.getGender());
        userDTO.setPhone(user.getPhone());
        userDTO.setAdresse(user.getAdresse());
        userDTO.setNationality(user.getNationality());
        userDTO.setCne(user.getCne());
        userDTO.setCni(user.getCni());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    @Override
    public User fromUserDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDateNaissance(userDTO.getDateNaissance());
        user.setGender(userDTO.getGender());
        user.setPhone(userDTO.getPhone());
        user.setAdresse(userDTO.getAdresse());
        user.setNationality(userDTO.getNationality());
        user.setCne(userDTO.getCne());
        user.setCni(userDTO.getCni());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
