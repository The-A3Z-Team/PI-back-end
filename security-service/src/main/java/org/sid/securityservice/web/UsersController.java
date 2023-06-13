package org.sid.securityservice.web;

import org.hibernate.Hibernate;
import org.sid.securityservice.dtos.RoleDTO;
import org.sid.securityservice.dtos.UserDTO;
import org.sid.securityservice.dtos.UserResponseDTO;
import org.sid.securityservice.ennumeration.ERole;
import org.sid.securityservice.exceptions.RoleNotFoundException;
import org.sid.securityservice.exceptions.UserNotFoundException;
import org.sid.securityservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    /*
        STUDENT, (done)
        FINANCIAL_OFFICIER,
        HEAD_OF_DEPARTEMENT,
        SCHOOLING,
        DEPUTY_MANAGER,
        IT_MANAGER,
        GENERAL_DIRECTOR
    */

    // FINANCIAL OFFICIER authorizations
    // Update her personel informations done
    // Get student by id done
    // Get student by keyword
    // Get student with semantic way
    // Get all students

    // STUDENT authorizations (done)
    // Update her personel informations

    // HEAD OF DEPARTEMENT
    // Update her personel informations
    // Get student by keyword
    // Get student with semantic way
    // Get all students
    // Update stude informations
    // Delete student

    // Schooling authorizations
    // Update her personel informations
    // Get student by keyword
    // Get student with semantic way
    // Get all students

    // Director & Director ADJ
    // Update her personel informations
    // Get user by keyword
    // Get user with semantic way
    // Get all users

    // IN manager
    // Update her personel informations
    // Get user by keyword
    // Get user with semantic way
    // Get all users
    // Update user informations
    // Delete user

    @PutMapping("/user/profile/{id}")
    public ResponseEntity<UserResponseDTO> putPersonelProfile(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            UserResponseDTO updatedUser = userService.updateUser(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /*@PreAuthorize("hasAuthority('HEAD_OF_DEPARTEMENT') " +
           "or hasAnyAuthority('IT_MANAGER') " +
           "or hasAuthority('DEPUTY_MANAGER') " +
           "or hasAuthority('SCHOOLING') " +
           "or hasAuthority('GENERAL_DIRECTOR') " +
           "or hasAuthority('FINANCIAL_OFFICER')")*/
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        try {
            UserResponseDTO user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /*@PreAuthorize("hasAuthority('HEAD_OF_DEPARTEMENT') " +
            "or hasAnyAuthority('IT_MANAGER') " +
            "or hasAuthority('DEPUTY_MANAGER') " +
            "or hasAuthority('SCHOOLING') " +
            "or hasAuthority('GENERAL_DIRECTOR') " +
            "or hasAuthority('FINANCIAL_OFFICER')")*/
    /*@GetMapping("/user/username/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username) {
        try {
            UserResponseDTO user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }*/

    @GetMapping("/user")
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        List<UserResponseDTO> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/student")
    public ResponseEntity<UserResponseDTO> saveStudent(@RequestBody UserDTO userDTO) {
        try {
            if (userDTO.getUsername() == null || userDTO.getPassword() == null || userDTO.getEmail() == null) {
                throw new IllegalArgumentException("Required fields are missing.");
            }

            UserResponseDTO savedUser = userService.saveUser(userDTO,String.valueOf(ERole.STUDENT));
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Return 400 Bad Request
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500 Internal Server Error
        }
    }

    @GetMapping("/student")
    public ResponseEntity<List<UserResponseDTO>> getStudents() {
        try {
            List<UserResponseDTO> users = userService.getUsersByRole(ERole.STUDENT);
            return ResponseEntity.ok(users);
        } catch (RoleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<UserResponseDTO> getStudentsById(@PathVariable Long id) {
        try {
            UserResponseDTO user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping("/user/save/{role}")
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserDTO userDTO,@PathVariable String role) {
        try {
            if (userDTO == null || userDTO.getUsername() == null || userDTO.getPassword() == null) {
                throw new IllegalArgumentException("Username and password are required fields.");
            }

            UserResponseDTO savedUser = userService.saveUser(userDTO,role.toUpperCase());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new UserResponseDTO("Error: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserResponseDTO("An unexpected error occurred."));
        }
    }



    @PutMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            UserResponseDTO updatedUser = userService.updateUser(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    /*@PreAuthorize("hasAuthority('HEAD_OF_DEPARTEMENT') " +
            "or hasAnyAuthority('IT_MANAGER') " +
            "or hasAuthority('DEPUTY_MANAGER') " +
            "or hasAuthority('SCHOOLING') " +
            "or hasAuthority('GENERAL_DIRECTOR') " +
            "or hasAuthority('FINANCIAL_OFFICER')")*/
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> removeUser(@PathVariable Long id) {
        try {
            UserResponseDTO user = userService.getUserById(id);
            Hibernate.initialize(user.getRoleDTOList());
            userService.removeUser(id);
            return ResponseEntity.ok("The user with ID " + id + " is deleted successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /*@PreAuthorize("hasAuthority('HEAD_OF_DEPARTEMENT') " +
            "or hasAnyAuthority('IT_MANAGER') " +
            "or hasAuthority('DEPUTY_MANAGER') " +
            "or hasAuthority('GENERAL_DIRECTOR')")*/
    @GetMapping("/user/role/{rolename}")
    public ResponseEntity<List<UserResponseDTO>> getUsersByRole(@PathVariable ERole rolename) {
        try {
            List<UserResponseDTO> users = userService.getUsersByRole(rolename);
            return ResponseEntity.ok(users);
        } catch (RoleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /*@PreAuthorize("hasAnyAuthority('IT_MANAGER')")*/
    @PostMapping("/user/{id}/role/add")
    public ResponseEntity<UserResponseDTO> addRoleToUser(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
        try {
            UserResponseDTO user = userService.addRoleToUser(id, roleDTO.getName());
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException | RoleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /*@PreAuthorize("hasAnyAuthority('IT_MANAGER')")*/
    @PostMapping("/user/{id}/role/remove")
    public ResponseEntity<UserResponseDTO> removeRoleFromUser(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
        try {
            UserResponseDTO user = userService.removeRoleFromUser(id, roleDTO);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (RoleNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
