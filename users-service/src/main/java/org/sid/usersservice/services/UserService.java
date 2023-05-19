package org.sid.usersservice.services;

import org.sid.usersservice.dtos.NotificationDTO;
import org.sid.usersservice.dtos.UserDTO;
import org.sid.usersservice.entities.User;
import org.sid.usersservice.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);

    UserDTO updateUser(Long id,UserDTO userDTO) throws UserNotFoundException;
    List<UserDTO> getUsers();
    UserDTO getUserById(Long id) throws UserNotFoundException;
    void deleteUser(Long id) throws UserNotFoundException;
    List<NotificationDTO> getNotificationsByUserId(Long id);
}
