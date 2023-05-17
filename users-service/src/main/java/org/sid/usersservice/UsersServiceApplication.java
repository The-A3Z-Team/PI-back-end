package org.sid.usersservice;

import org.sid.usersservice.entity.Role;
import org.sid.usersservice.entity.User;
import org.sid.usersservice.repositorys.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class UsersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersServiceApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository
                                               ){
        return args -> {
            Role role=new Role();
            role.setName("student");

            User u=new User();
            u.setLogin("oiuzjef");
            u.setPassword("oizjfiozfj");
            u.setCne("oirefjirf");
            u.setAddress("poejfio");
            u.setFirstName("iouheruf");
            u.setLastName("iuhuf");
            //u.addRole(role);
            userRepository.save(u);
        };
    }
}
