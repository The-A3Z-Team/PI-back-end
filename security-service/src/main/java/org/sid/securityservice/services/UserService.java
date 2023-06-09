package org.sid.securityservice.services;

import org.sid.securityservice.dtos.RoleDTO;
import org.sid.securityservice.dtos.UserDTO;
import org.sid.securityservice.exceptions.RoleNotFoundException;
import org.sid.securityservice.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDTO saveUser(UserDTO userDTO);
    UserDTO updateUser(Long id,UserDTO userDTO) throws UserNotFoundException;
    List<UserDTO> getUsers();
    List<UserDTO> getUserById(Long id);
    UserDTO getUserByUsername(String username) throws UserNotFoundException;
    UserDTO removeUser(Long idUser) throws UserNotFoundException;
    List<UserDTO> getUsersByRole(String role) throws RoleNotFoundException;
    UserDTO addRoleToUser(Long idUser,RoleDTO roleDTO) throws UserNotFoundException;
    UserDTO removeRoleFromUser(Long idUser,RoleDTO roleDTO) throws UserNotFoundException;
}
