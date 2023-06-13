package org.sid.securityservice.services;

import org.sid.securityservice.dtos.NotificationResponseDTO;
import org.sid.securityservice.dtos.RoleDTO;
import org.sid.securityservice.dtos.UserDTO;
import org.sid.securityservice.dtos.UserResponseDTO;
import org.sid.securityservice.ennumeration.ERole;
import org.sid.securityservice.exceptions.RoleNotFoundException;
import org.sid.securityservice.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserResponseDTO saveUser(UserDTO userDTO,String role) throws RoleNotFoundException;
    UserResponseDTO updateUser(Long id, UserDTO userDTO) throws UserNotFoundException;
    List<UserResponseDTO> getUsers();
    UserResponseDTO getUserById(Long id) throws UserNotFoundException;
    UserResponseDTO getUserByUsername(String username) throws UserNotFoundException;
    UserResponseDTO removeUser(Long idUser) throws UserNotFoundException;
    List<UserResponseDTO> getUsersByRole(ERole role) throws RoleNotFoundException;
    UserResponseDTO addRoleToUser(Long idUser, String roleName) throws UserNotFoundException, RoleNotFoundException;
    UserResponseDTO removeRoleFromUser(Long idUser,RoleDTO roleDTO) throws UserNotFoundException, RoleNotFoundException;
    List<NotificationResponseDTO> getNotificationsByUser(Long userId) throws UserNotFoundException;
    List<UserResponseDTO> getUsersByMajor(Long idMajor);
}
