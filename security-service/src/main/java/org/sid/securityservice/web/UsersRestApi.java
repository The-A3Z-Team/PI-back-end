package org.sid.securityservice.web;

import org.sid.securityservice.dtos.RoleDTO;
import org.sid.securityservice.dtos.UserDTO;
import org.sid.securityservice.exceptions.RoleNotFoundException;
import org.sid.securityservice.exceptions.UserNotFoundException;
import org.sid.securityservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersRestApi {
    private final UserService userService;

    public UsersRestApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) throws UserNotFoundException {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        List<UserDTO> users = userService.getUserById(id);
        return ResponseEntity.ok(users.get(0));
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<UserDTO> removeUser(@PathVariable Long idUser) throws UserNotFoundException {
        UserDTO user = userService.removeUser(idUser);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) throws UserNotFoundException {
        UserDTO user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/role/{rolename}")
    public ResponseEntity<List<UserDTO>> getUsersByRole(@PathVariable String rolename) throws RoleNotFoundException {
        List<UserDTO> users = userService.getUsersByRole(rolename);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{idUser}/roles")
    public ResponseEntity<UserDTO> addRoleToUser(@PathVariable Long idUser, @RequestBody RoleDTO roleDTO) throws UserNotFoundException {
        UserDTO user = userService.addRoleToUser(idUser, roleDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{idUser}/roles")
    public ResponseEntity<UserDTO> removeRoleFromUser(@PathVariable Long idUser, @RequestBody RoleDTO roleDTO) throws UserNotFoundException {
        UserDTO user = userService.removeRoleFromUser(idUser, roleDTO);
        return ResponseEntity.ok(user);
    }
}
