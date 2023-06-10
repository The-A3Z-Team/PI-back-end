package org.sid.securityservice.web;

import org.sid.securityservice.dtos.RoleDTO;
import org.sid.securityservice.dtos.UserDTO;
import org.sid.securityservice.dtos.UserResponseDTO;
import org.sid.securityservice.exceptions.RoleNotFoundException;
import org.sid.securityservice.exceptions.UserNotFoundException;
import org.sid.securityservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserDTO userDTO) {
        UserResponseDTO savedUser = userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) throws UserNotFoundException {
        UserResponseDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        List<UserResponseDTO> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) throws UserNotFoundException {
        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<UserResponseDTO> removeUser(@PathVariable Long idUser) throws UserNotFoundException {
        UserResponseDTO user = userService.removeUser(idUser);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username) throws UserNotFoundException {
        UserResponseDTO user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/role/{rolename}")
    public ResponseEntity<List<UserResponseDTO>> getUsersByRole(@PathVariable String rolename) throws RoleNotFoundException {
        List<UserResponseDTO> users = userService.getUsersByRole(rolename);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{idUser}/roles")
    public ResponseEntity<UserResponseDTO> addRoleToUser(@PathVariable Long idUser, @RequestBody RoleDTO roleDTO) throws UserNotFoundException {
        UserResponseDTO user = userService.addRoleToUser(idUser, roleDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{idUser}/roles")
    public ResponseEntity<UserResponseDTO> removeRoleFromUser(@PathVariable Long idUser, @RequestBody RoleDTO roleDTO) throws UserNotFoundException {
        UserResponseDTO user = userService.removeRoleFromUser(idUser, roleDTO);
        return ResponseEntity.ok(user);
    }
}
