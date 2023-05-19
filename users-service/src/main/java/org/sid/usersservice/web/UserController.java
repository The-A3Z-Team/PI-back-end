package org.sid.usersservice.web;

import lombok.AllArgsConstructor;
import org.sid.usersservice.dtos.NotificationDTO;
import org.sid.usersservice.dtos.UserDTO;
import org.sid.usersservice.exceptions.UserNotFoundException;
import org.sid.usersservice.services.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {
    private UserServiceImpl userService;

    @GetMapping("/")
    public List<UserDTO> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @PostMapping("/")
    public UserDTO addUser(@RequestBody UserDTO user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable("id") Long id, @RequestBody UserDTO user) throws UserNotFoundException {
        return userService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}/notifications")
    public List<NotificationDTO> getNotificationsByUserId(@PathVariable("id") Long userId) {
        return userService.getNotificationsByUserId(userId);
    }
}

