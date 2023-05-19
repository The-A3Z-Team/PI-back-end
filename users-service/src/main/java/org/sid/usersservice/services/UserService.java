package org.sid.usersservice.services;

import org.sid.usersservice.entities.User;
import org.sid.usersservice.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User updateUser(User user) throws UserNotFoundException;
    List<User> getUsers();
    User getUserById(Long id);
    void deleteUser(Long id);
}
