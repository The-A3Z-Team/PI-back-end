package org.sid.securityservice.web;

import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.sid.securityservice.dtos.NotificationResponseDTO;
import org.sid.securityservice.dtos.UserDTO;
import org.sid.securityservice.dtos.UserResponseDTO;
import org.sid.securityservice.ennumeration.ERole;
import org.sid.securityservice.exceptions.RoleNotFoundException;
import org.sid.securityservice.exceptions.UserNotFoundException;
import org.sid.securityservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

public class GeneralDirectorController {
    private UserService userService;
    @GetMapping("/general_director")
    public ResponseEntity<List<UserResponseDTO>> getGeneralDirectors() {
        try {
            List<UserResponseDTO> users = userService.getUsersByRole(ERole.GENERAL_DIRECTOR);
            return ResponseEntity.ok(users);
        } catch (RoleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/general_director/{id}")
    public ResponseEntity<UserResponseDTO> getGeneralDirectorById(@PathVariable Long id) {
        try {
            UserResponseDTO user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/general_director")
    public ResponseEntity<UserResponseDTO> saveGeneralDirector(@RequestBody UserDTO userDTO) {
        try {
            if (userDTO.getUsername() == null || userDTO.getPassword() == null || userDTO.getEmail() == null) {
                throw new IllegalArgumentException("Required fields are missing.");
            }

            UserResponseDTO savedUser = userService.saveUser(userDTO,String.valueOf(ERole.GENERAL_DIRECTOR));
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Return 400 Bad Request
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500 Internal Server Error
        }
    }

    @DeleteMapping("/general_director/{id}")
    public ResponseEntity<String> removeGeneralDirector(@PathVariable Long id) {
        try {
            UserResponseDTO user = userService.getUserById(id);
            Hibernate.initialize(user.getRoleDTOList());
            userService.removeUser(id);
            return ResponseEntity.ok("The general director with ID " + id + " is deleted successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/general_director/{id}")
    public ResponseEntity<UserResponseDTO> putGeneralDirector(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            UserResponseDTO updatedUser = userService.updateUser(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/general_director/{id}/notifications")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsByGeneralDirector(@PathVariable Long id) throws UserNotFoundException {
        List<NotificationResponseDTO> notifications = userService.getNotificationsByUser(id);
        return ResponseEntity.ok(notifications);
    }
}
