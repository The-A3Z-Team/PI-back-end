package org.sid.usersservice.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sid.usersservice.entity.Person;
import org.sid.usersservice.entity.User;
import org.sid.usersservice.repositorys.PersonRepository;
import org.sid.usersservice.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@NoArgsConstructor
public class UserController {
    /*@Autowired
    private UserRepository userRepository;*/
    @Autowired
    private UserRepository userRepository;

    /*@GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        System.out.println((users.size()));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }*/

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllPeople() {
        List<User> users = userRepository.findAll();
        System.out.println((users.size()));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
