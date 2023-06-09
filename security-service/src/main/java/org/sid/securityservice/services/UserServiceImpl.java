package org.sid.securityservice.services;

import lombok.AllArgsConstructor;
import org.sid.securityservice.dtos.RoleDTO;
import org.sid.securityservice.dtos.UserDTO;
import org.sid.securityservice.entities.ERole;
import org.sid.securityservice.entities.Role;
import org.sid.securityservice.entities.User;
import org.sid.securityservice.exceptions.RoleNotFoundException;
import org.sid.securityservice.exceptions.UserNotFoundException;
import org.sid.securityservice.mappers.RoleMapperImpl;
import org.sid.securityservice.mappers.UserMapperImpl;
import org.sid.securityservice.repositories.RoleRepository;
import org.sid.securityservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private UserMapperImpl userMapper;
    private RoleRepository roleRepository;
    private RoleMapperImpl roleMapper;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user=userMapper.fromUserDTO(userDTO);
        User savedUser=userRepository.save(user);
        return userMapper.fromUser(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id,UserDTO userDTO) throws UserNotFoundException {
        User existingUser = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(" User not found with ID: " + id));

        existingUser.setCne(userDTO.getCne());
        existingUser.setAdresse(userDTO.getAdresse());
        existingUser.setCni(userDTO.getCni());
        existingUser.setGender(userDTO.getGender());
        existingUser.setNationality(userDTO.getNationality());
        existingUser.setAdresse(userDTO.getAdresse());
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setDateNaissance(userDTO.getDateNaissance());
        existingUser.setPhone(userDTO.getPhone());

        User updatedUser = userRepository.save(existingUser);
        return userMapper.fromUser(updatedUser);
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDTOs(users);
    }

    @Override
    public List<UserDTO> getUserById(Long id) {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDTOs(users);
    }
    @Override
    public UserDTO removeUser(Long idUser) throws UserNotFoundException{
        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException(" User not found with ID: " + idUser));
        userRepository.delete(existingUser);
        return userMapper.fromUser(existingUser);
    }

    @Override
    public UserDTO getUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(" User not found with Username: " + username));
        return userMapper.fromUser(user);
    }

    @Override
    public List<UserDTO> getUsersByRole(String rolename) throws RoleNotFoundException {
        Role role=roleRepository.findByName(ERole.valueOf(rolename))
                .orElseThrow(() -> new RoleNotFoundException(" Role not found with Username: " + rolename));

        List<User> users = userRepository.getUsersByRolesContains(role);
        return userMapper.toUserDTOs(users);
    }

    @Override
    public UserDTO addRoleToUser(Long idUser, RoleDTO roleDTO) throws UserNotFoundException {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException(" User not found with Username: " + idUser));
        user.getRoles().add(roleMapper.fromRoleDTO(roleDTO));
        return userMapper.fromUser(user);
    }

    @Override
    public UserDTO removeRoleFromUser(Long idUser,RoleDTO roleDTO) throws UserNotFoundException {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException(" User not found with Username: " + idUser));
        user.getRoles().remove(roleMapper.fromRoleDTO(roleDTO));
        return userMapper.fromUser(user);
    }
}
