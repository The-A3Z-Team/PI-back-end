package org.sid.usersservice.mappers;

import org.sid.usersservice.dtos.UserDTO;
import org.sid.usersservice.entities.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapperImpl {
    public UserDTO fromUser(User user){
        UserDTO userDTO=new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return  userDTO;
    }

    public User fromUserDTO(UserDTO userDTO){
        User user=new User();
        BeanUtils.copyProperties(userDTO,user);
        return  user;
    }

    public List<UserDTO> toUserDTOs(List<User> users) {
        return users.stream()
                .map(this::fromUser)
                .collect(Collectors.toList());
    }
}
