package org.sid.usersservice.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.usersservice.dtos.UserDTO;
import org.sid.usersservice.entities.User;
import org.sid.usersservice.exceptions.UserNotFoundException;
import org.sid.usersservice.mappers.UserMapperImpl;
import org.sid.usersservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapperImpl userMapper;


    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        log.info("Saving new User");
        User user = userMapper.fromUserDTO(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.fromUser(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) throws UserNotFoundException {
        log.info("Updating User with ID: {}", userDTO.getId());
        User existingUser = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userDTO.getId()));
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        User updatedUser = userRepository.save(existingUser);
        return userMapper.fromUser(updatedUser);
    }

    @Override
    public List<UserDTO> getUsers() {
        log.info("Fetching all Users");
        List<User> users = userRepository.findAll();
        return userMapper.toUserDTOs(users);
    }

    @Override
    public UserDTO getUserById(Long id) throws UserNotFoundException {
        log.info("Fetching User with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        return userMapper.fromUser(user);
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        log.info("Deleting User with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        userRepository.delete(user);
    }
}
