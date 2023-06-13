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

public class FinancialOfficierController {
    private UserService userService;
    @GetMapping("/financial_officier")
    public ResponseEntity<List<UserResponseDTO>> getFinancialOfficiers() {
        try {
            List<UserResponseDTO> users = userService.getUsersByRole(ERole.DEPUTY_MANAGER);
            return ResponseEntity.ok(users);
        } catch (RoleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/financial_officier/{id}")
    public ResponseEntity<UserResponseDTO> getFinancialOfficierById(@PathVariable Long id) {
        try {
            UserResponseDTO user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/financial_officier")
    public ResponseEntity<UserResponseDTO> saveFinancialOfficier(@RequestBody UserDTO userDTO) throws RoleNotFoundException {
        UserResponseDTO savedUser = userService.saveUser(userDTO, String.valueOf(ERole.FINANCIAL_OFFICIER));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/financial_officier/{id}")
    public ResponseEntity<UserResponseDTO> putFinancialDirector(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            UserResponseDTO updatedUser = userService.updateUser(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/financial_officier/{id}/notifications")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsByFinancialOfficier(@PathVariable Long id) throws UserNotFoundException {
        List<NotificationResponseDTO> notifications = userService.getNotificationsByUser(id);
        return ResponseEntity.ok(notifications);
    }
}
