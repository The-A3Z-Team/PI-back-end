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

public class DeputyManagerController {
    private UserService userService;
    @GetMapping("/deputy_manager")
    public ResponseEntity<List<UserResponseDTO>> getDeputyManagers() {
        try {
            List<UserResponseDTO> users = userService.getUsersByRole(ERole.DEPUTY_MANAGER);
            return ResponseEntity.ok(users);
        } catch (RoleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/deputy_manager/{id}")
    public ResponseEntity<UserResponseDTO> getDeputyManagerById(@PathVariable Long id) {
        try {
            UserResponseDTO user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/deputy_manager/keyword/{keyword}")
    public ResponseEntity<List<UserResponseDTO>> getStudentsByMajor(@PathVariable String keyword) {
        List<UserResponseDTO> users = userService.getUsersByKeyword(keyword,String.valueOf(ERole.DEPUTY_MANAGER));
        return ResponseEntity.ok(users);
    }

    @PostMapping("/deputy_manager")
    public ResponseEntity<UserResponseDTO> saveDeputyManager(@RequestBody UserDTO userDTO) throws RoleNotFoundException {
        UserResponseDTO savedUser = userService.saveUser(userDTO, String.valueOf(ERole.DEPUTY_MANAGER));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping("/deputy_manager/{id}")
    public ResponseEntity<String> removeDeputyManager(@PathVariable Long id) {
        try {
            UserResponseDTO user = userService.getUserById(id);
            Hibernate.initialize(user.getRoleDTOList());
            userService.removeUser(id);
            return ResponseEntity.ok("The deputy manager with ID " + id + " is deleted successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PutMapping("/deputy_manager/{id}")
    public ResponseEntity<UserResponseDTO> putDeputyManager(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            UserResponseDTO updatedUser = userService.updateUser(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/deputy_manager/{id}/notifications")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsByDeputyManager(@PathVariable Long id) throws UserNotFoundException {
        List<NotificationResponseDTO> notifications = userService.getNotificationsByUser(id);
        return ResponseEntity.ok(notifications);
    }
}
