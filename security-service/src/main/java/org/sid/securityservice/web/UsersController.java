package org.sid.securityservice.web;

import org.hibernate.Hibernate;
import org.sid.securityservice.dtos.RoleDTO;
import org.sid.securityservice.dtos.UserDTO;
import org.sid.securityservice.dtos.UserResponseDTO;
import org.sid.securityservice.exceptions.RoleNotFoundException;
import org.sid.securityservice.exceptions.UserNotFoundException;
import org.sid.securityservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('HEAD_OF_DEPARTEMENT')")
    @PostMapping("/student")
    public ResponseEntity<UserResponseDTO> saveStudent(@RequestBody UserDTO userDTO) {
        if (userDTO.getRoleDTOList().contains("STUDENT")) {
            UserResponseDTO savedUser = userService.saveUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PreAuthorize("hasAuthority('HEAD_OF_DEPARTEMENT')")
    @PutMapping("/student/{id}")
    public ResponseEntity<UserResponseDTO> updateStudent(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        if (userDTO.getRoleDTOList().contains("STUDENT")) {
            try {
                UserResponseDTO updatedUser = userService.updateUser(id, userDTO);
                return ResponseEntity.ok(updatedUser);
            } catch (UserNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PreAuthorize("hasAnyAuthority('HEAD_OF_DEPARTEMENT')")
    @DeleteMapping("/student/{idUser}")
    public ResponseEntity<String> removeStudent(@PathVariable Long idUser) {
        try {
            UserResponseDTO user = userService.getUserById(idUser);
            if (user.getRoleDTOList().contains("STUDENT")) {
                Hibernate.initialize(user.getRoleDTOList());
                userService.removeUser(idUser);
                return ResponseEntity.ok("The user with ID " + idUser + " is deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PreAuthorize("hasAnyAuthority('HEAD_OF_DEPARTEMENT','SCHOOLING','FINANCIAL_OFFICIER')")
    @GetMapping("/students")
    public ResponseEntity<List<UserResponseDTO>> getStudents() {
        try {
            List<UserResponseDTO> users = userService.getUsersByRole("STUDENT");
            return ResponseEntity.ok(users);
        } catch (RoleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PreAuthorize("hasAuthority('IT_MANAGER')")
    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserDTO userDTO) {
        UserResponseDTO savedUser = userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PreAuthorize("hasAuthority('IT_MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            UserResponseDTO updatedUser = userService.updateUser(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PreAuthorize("hasAnyAuthority('IT_MANAGER','DEPUTY_MANAGER','GENERAL_DIRECTOR')")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        List<UserResponseDTO> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAuthority('HEAD_OF_DEPARTEMENT') " +
            "or hasAnyAuthority('IT_MANAGER') " +
            "or hasAuthority('DEPUTY_MANAGER') " +
            "or hasAuthority('SCHOOLING') " +
            "or hasAuthority('GENERAL_DIRECTOR') " +
            "or hasAuthority('FINANCIAL_OFFICIER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        try {
            UserResponseDTO user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PreAuthorize("hasAuthority('HEAD_OF_DEPARTEMENT') " +
            "or hasAnyAuthority('IT_MANAGER') " +
            "or hasAuthority('DEPUTY_MANAGER') " +
            "or hasAuthority('SCHOOLING') " +
            "or hasAuthority('GENERAL_DIRECTOR') " +
            "or hasAuthority('FINANCIAL_OFFICIER')")
    @DeleteMapping("/{idUser}")
    public ResponseEntity<String> removeUser(@PathVariable Long idUser) {
        try {
            UserResponseDTO user = userService.getUserById(idUser);
            Hibernate.initialize(user.getRoleDTOList());
            userService.removeUser(idUser);
            return ResponseEntity.ok("The user with ID " + idUser + " is deleted successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PreAuthorize("hasAuthority('HEAD_OF_DEPARTEMENT') " +
            "or hasAnyAuthority('IT_MANAGER') " +
            "or hasAuthority('DEPUTY_MANAGER') " +
            "or hasAuthority('SCHOOLING') " +
            "or hasAuthority('GENERAL_DIRECTOR') " +
            "or hasAuthority('FINANCIAL_OFFICIER')")
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username) {
        try {
            UserResponseDTO user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PreAuthorize("hasAuthority('HEAD_OF_DEPARTEMENT') " +
            "or hasAnyAuthority('IT_MANAGER') " +
            "or hasAuthority('DEPUTY_MANAGER') " +
            "or hasAuthority('GENERAL_DIRECTOR')")
    @GetMapping("/role/{rolename}")
    public ResponseEntity<List<UserResponseDTO>> getUsersByRole(@PathVariable String rolename) {
        try {
            List<UserResponseDTO> users = userService.getUsersByRole(rolename);
            return ResponseEntity.ok(users);
        } catch (RoleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    @PreAuthorize("hasAnyAuthority('IT_MANAGER')")
    @PostMapping("/{idUser}/roles/add")
    public ResponseEntity<UserResponseDTO> addRoleToUser(@PathVariable Long idUser, @RequestBody RoleDTO roleDTO) {
        try {
            UserResponseDTO user = userService.addRoleToUser(idUser, roleDTO.getName());
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException | RoleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PreAuthorize("hasAnyAuthority('IT_MANAGER')")
    @PostMapping("/{idUser}/roles/remove")
    public ResponseEntity<UserResponseDTO> removeRoleFromUser(@PathVariable Long idUser, @RequestBody RoleDTO roleDTO) {
        try {
            UserResponseDTO user = userService.removeRoleFromUser(idUser, roleDTO);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (RoleNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
