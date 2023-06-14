package org.sid.securityservice.web;

import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.sid.securityservice.dtos.*;
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
public class StudentController {
    private UserService userService;

    @GetMapping("/student")
    public ResponseEntity<List<UserResponseDTO>> getStudents() {
        try {
            List<UserResponseDTO> users = userService.getUsersByRole(ERole.STUDENT);
            return ResponseEntity.ok(users);
        } catch (RoleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/student/{id_student}/major")
    public ResponseEntity<MajorResponseDTO> getMajorByStudent(@PathVariable Long id_student) throws UserNotFoundException {
        MajorResponseDTO major= userService.getMajorOfStudent(id_student);
        return ResponseEntity.ok(major);
    }

    @GetMapping("/student/{id_student}/education")
    public ResponseEntity<EducationDTO> getEducationOfStudent(@PathVariable Long id_student) throws UserNotFoundException {
        EducationDTO education= userService.getEducationOfStudent(id_student);
        return ResponseEntity.ok(education);
    }

    @GetMapping("/student/keyword/{keyword}")
    public ResponseEntity<List<UserResponseDTO>> getStudentsByKeyword(@PathVariable String keyword) {
        List<UserResponseDTO> users = userService.getUsersByKeyword(keyword,String.valueOf(ERole.STUDENT));
        return ResponseEntity.ok(users);
    }

    @GetMapping("/student/major/{id}")
    public ResponseEntity<List<UserResponseDTO>> getStudentsByMajor(@PathVariable Long id) {
        List<UserResponseDTO> users = userService.getUsersByMajor(id);
        return ResponseEntity.ok(users);
    }

    /*@GetMapping("/student/{id}/major")
    public ResponseEntity<MajorResponseDTO> getMajorByStudent(@PathVariable Long id) throws UserNotFoundException {
        MajorResponseDTO majorResponseDTO = userService.getMajorByStudent(id);
        return ResponseEntity.ok(majorResponseDTO);
    }*/

    @GetMapping("/student/{id}")
    public ResponseEntity<UserResponseDTO> getStudentById(@PathVariable Long id) {
        try {
            UserResponseDTO user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/student")
    public ResponseEntity<UserResponseDTO> saveStudent(@RequestBody UserDTO userDTO) throws RoleNotFoundException {
        UserResponseDTO savedUser = userService.saveUser(userDTO, String.valueOf(ERole.STUDENT));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> removeStudent(@PathVariable Long id) {
        try {
            UserResponseDTO user = userService.getUserById(id);
            Hibernate.initialize(user.getRoleDTOList());
            userService.removeUser(id);
            return ResponseEntity.ok("The student with ID " + id + " is deleted successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<UserResponseDTO> putStudent(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            UserResponseDTO updatedUser = userService.updateUser(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/student/{id}/notifications")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsByUser(@PathVariable Long id) throws UserNotFoundException {
        List<NotificationResponseDTO> notifications = userService.getNotificationsByUser(id);
        return ResponseEntity.ok(notifications);
    }
}
