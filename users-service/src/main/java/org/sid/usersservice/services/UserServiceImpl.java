package org.sid.usersservice.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.usersservice.entities.User;
import org.sid.usersservice.exceptions.UserNotFoundException;
import org.sid.usersservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        log.info("Saving new User");
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        log.info("Update User");
        User existedUser = userRepository.findById(user.getId()).orElse(null);
        if (existedUser == null)
            throw new UserNotFoundException("User not found");

        existedUser.setEmail(user.getEmail());
        existedUser.setPassword(user.getPassword());
        existedUser.setLogin(user.getLogin());
        existedUser.setCne(user.getCne());
        existedUser.setDateNaissance(user.getDateNaissance());

        User updatedUser = userRepository.save(existedUser);
        return updatedUser;
    }

    @Override
    public List<User> getUsers() {
        log.info("Get all Users");
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        log.info("Get User by ID: {}", id);
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            throw new UserNotFoundException("User not found");

        return user;
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        log.info("Delete User with ID: {}", id);
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            throw new UserNotFoundException("User not found");

        userRepository.deleteById(id);
    }
}
