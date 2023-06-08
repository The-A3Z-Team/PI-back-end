package org.sid.securityservice.web;

import org.sid.securityservice.entities.User;
import org.sid.securityservice.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TestRestAPI {
    private UserRepository userRepository;
    public TestRestAPI(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/dataTest")
    public Map<String, Object> dataTest(Authentication authentication) {
        return Map.of(
                "message", "Data test",
                "username", authentication.getName(),
                "authorities", authentication.getAuthorities()
        );
    }

    @PostMapping("/saveData")
    public Map<String, String> saveData(String data) {
        return Map.of("dataSaved", data);
    }

    @GetMapping("/student")
    public ResponseEntity<User> getStudentByEmail(@RequestParam String email){
        User user = this.userRepository.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
