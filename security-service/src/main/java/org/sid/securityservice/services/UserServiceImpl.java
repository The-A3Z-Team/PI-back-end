package org.sid.securityservice.services;

import lombok.AllArgsConstructor;
import org.sid.securityservice.config.PasswordEncoding;
import org.sid.securityservice.dtos.RoleDTO;
import org.sid.securityservice.dtos.UserDTO;
import org.sid.securityservice.dtos.UserResponseDTO;
import org.sid.securityservice.entities.ERole;
import org.sid.securityservice.entities.Role;
import org.sid.securityservice.entities.User;
import org.sid.securityservice.exceptions.RoleAlreadyAssignedException;
import org.sid.securityservice.exceptions.RoleNotFoundException;
import org.sid.securityservice.exceptions.UserNotFoundException;
import org.sid.securityservice.mappers.RoleMapper;
import org.sid.securityservice.mappers.UserDTOMapper;
import org.sid.securityservice.mappers.UserResponseDTOMapperImpl;
import org.sid.securityservice.repositories.RoleRepository;
import org.sid.securityservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDTOMapper userDTOMapper;
    private final UserResponseDTOMapperImpl userResponseDTOMapper;
    private final RoleMapper roleMapper;

    @Override
    public UserResponseDTO saveUser(UserDTO userDTO) {
        userDTO.setPassword(new PasswordEncoding().getEncodedPassword(userDTO.getPassword()));
        User user = userDTOMapper.fromUserDTO(userDTO);
        User savedUser = userRepository.save(user);
        return userResponseDTOMapper.fromUser(savedUser);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserDTO userDTO) throws UserNotFoundException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        if (userDTO.getCne() != null) {
            existingUser.setCne(userDTO.getCne());
        }
        if (userDTO.getAdresse() != null) {
            existingUser.setAdresse(userDTO.getAdresse());
        }
        if (userDTO.getCni() != null) {
            existingUser.setCni(userDTO.getCni());
        }
        if (userDTO.getGender() != null) {
            existingUser.setGender(userDTO.getGender());
        }
        if (userDTO.getNationality() != null) {
            existingUser.setNationality(userDTO.getNationality());
        }
        if (userDTO.getFirstName() != null) {
            existingUser.setFirstName(userDTO.getFirstName());
        }
        if (userDTO.getLastName() != null) {
            existingUser.setLastName(userDTO.getLastName());
        }
        if (userDTO.getEmail() != null) {
            existingUser.setEmail(userDTO.getEmail());
        }
        if (userDTO.getDateNaissance() != null) {
            existingUser.setDateNaissance(userDTO.getDateNaissance());
        }
        if (userDTO.getPhone() != null) {
            existingUser.setPhone(userDTO.getPhone());
        }
        if (userDTO.getPassword() != null) {
            existingUser.setPassword(new PasswordEncoding().getEncodedPassword(userDTO.getPassword()));
        }

        User updatedUser = userRepository.save(existingUser);
        return userResponseDTOMapper.fromUser(updatedUser);
    }


    @Override
    public List<UserResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return userResponseDTOMapper.toUserResponseDTOs(users);
    }

    @Override
    public UserResponseDTO getUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        return userResponseDTOMapper.fromUser(user);
    }

    @Override
    public UserResponseDTO removeUser(Long idUser) throws UserNotFoundException {
        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + idUser));
        userRepository.delete(existingUser);
        return userResponseDTOMapper.fromUser(existingUser);
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with Username: " + username));
        return userResponseDTOMapper.fromUser(user);
    }

    @Override
    public List<UserResponseDTO> getUsersByRole(String roleName) throws RoleNotFoundException {
        Role role = roleRepository.findByName(ERole.valueOf(roleName))
                .orElseThrow(() -> new RoleNotFoundException("Role not found with Name: " + roleName));

        List<User> users = userRepository.getUsersByRolesContains(role);
        return userResponseDTOMapper.toUserResponseDTOs(users);
    }

    @Override
    public UserResponseDTO addRoleToUser(Long idUser, String roleName) throws UserNotFoundException, RoleNotFoundException {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + idUser));

        // Check if the user already has the role
        if (user.getRoles().stream().anyMatch(existingRole -> existingRole.getName().equals(roleName))) {
            throw new RoleAlreadyAssignedException("Role already assigned to the user.");
        }

        Role newRole = roleRepository.findByName(ERole.valueOf(roleName))
                .orElseThrow(() -> new RoleNotFoundException("Role not found: " + roleName));

        user.getRoles().add(newRole);
        userRepository.save(user);

        return userResponseDTOMapper.fromUser(user);
    }



    @Override
    public UserResponseDTO removeRoleFromUser(Long idUser, RoleDTO roleDTO) throws UserNotFoundException, RoleNotFoundException {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + idUser));

        // Find the role by name
        Role role = roleRepository.findByName(ERole.valueOf(roleDTO.getName()))
                .orElseThrow(() -> new RoleNotFoundException("Role not found: " + roleDTO.getName()));

        // Remove the role from the user's roles
        user.getRoles().removeIf(existingRole -> existingRole.getName().equals(role.getName()));
        userRepository.save(user);

        return userResponseDTOMapper.fromUser(user);
    }

}
