package org.sid.usersservice.web;

import lombok.AllArgsConstructor;
import org.sid.usersservice.dtos.UserDTO;
import org.sid.usersservice.exceptions.UserNotFoundException;
import org.sid.usersservice.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

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
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        userService.deleteUser(id);
    }

    /*@GetMapping("/{id}/notifications")
    public ResponseEntity<List<Notification>> getNotificationsByUserId(@PathVariable("id") Long userId) {
        String url = "http://localhost:8093/api/notifications/user/" + userId;
        ResponseEntity<List<Notification>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Notification>>() {});
        if (response.getStatusCode() == HttpStatus.OK) {
            List<Notification> notifications = response.getBody();
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
}

