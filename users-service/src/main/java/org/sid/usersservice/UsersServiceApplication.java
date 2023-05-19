package org.sid.usersservice;

import org.sid.usersservice.dtos.UserDTO;
import org.sid.usersservice.entities.Permission;
import org.sid.usersservice.entities.Role;
import org.sid.usersservice.entities.User;
import org.sid.usersservice.enums.Gender;
import org.sid.usersservice.repositories.PermissionRepository;
import org.sid.usersservice.repositories.RoleRepository;
import org.sid.usersservice.repositories.UserRepository;
import org.sid.usersservice.services.UserServiceImpl;
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
    public CommandLineRunner commandLineRunner(UserServiceImpl userService) {
        return args -> {
            /*Permission permission=new Permission();
            permission.setName("add");
            permissionRepository.save(permission);

            List<Permission> permissions = new ArrayList<>();
            permissions.add(permission);

            Role role = new Role();
            role.setPermissions(permissions);
            role.setName("student");
            roleRepository.save(role);*/


            UserDTO u = new UserDTO();
            u.setFirstName("wac");
            u.setLastName("rca");


            userService.saveUser(u);
        };
    }
}
