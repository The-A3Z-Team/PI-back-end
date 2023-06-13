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

public class SchoolingController {
    private UserService userService;
    @GetMapping("/schooling")
    public ResponseEntity<List<UserResponseDTO>> getSchoolings() {
        try {
            List<UserResponseDTO> users = userService.getUsersByRole(ERole.SCHOOLING);
            return ResponseEntity.ok(users);
        } catch (RoleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/schooling/{id}")
    public ResponseEntity<UserResponseDTO> getSchoolingById(@PathVariable Long id) {
        try {
            UserResponseDTO user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/schooling/keyword/{keyword}")
    public ResponseEntity<List<UserResponseDTO>> getStudentsByMajor(@PathVariable String keyword) {
        List<UserResponseDTO> users = userService.getUsersByKeyword(keyword,String.valueOf(ERole.SCHOOLING));
        return ResponseEntity.ok(users);
    }

    @PostMapping("/schooling")
    public ResponseEntity<UserResponseDTO> saveSchooling(@RequestBody UserDTO userDTO) throws RoleNotFoundException {
        UserResponseDTO savedUser = userService.saveUser(userDTO, String.valueOf(ERole.SCHOOLING));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping("/schooling/{id}")
    public ResponseEntity<String> removeUser(@PathVariable Long id) {
        try {
            UserResponseDTO user = userService.getUserById(id);
            Hibernate.initialize(user.getRoleDTOList());
            userService.removeUser(id);
            return ResponseEntity.ok("The schooling with ID " + id + " is deleted successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/schooling/{id}")
    public ResponseEntity<UserResponseDTO> putSchooling(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            UserResponseDTO updatedUser = userService.updateUser(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/schooling/{id}/notifications")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsBySchooling(@PathVariable Long id) throws UserNotFoundException {
        List<NotificationResponseDTO> notifications = userService.getNotificationsByUser(id);
        return ResponseEntity.ok(notifications);
    }
}
