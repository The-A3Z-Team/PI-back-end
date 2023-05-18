package org.sid.usersservice;

import org.sid.usersservice.entity.Permission;
import org.sid.usersservice.entity.Role;
import org.sid.usersservice.entity.User;
import org.sid.usersservice.repositorys.PermissionRepository;
import org.sid.usersservice.repositorys.RoleRepository;
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
    public CommandLineRunner commandLineRunner(UserRepository userRepository, RoleRepository roleRepository
    , PermissionRepository permissionRepository) {
        return args -> {
            Permission permission=new Permission();
            permission.setName("add");
            permissionRepository.save(permission);

            List<Permission> permissions = new ArrayList<>();
            permissions.add(permission);

            Role role = new Role();
            role.setPermissions(permissions);
            role.setName("student");
            roleRepository.save(role);


            User u = new User();
            u.setLogin("oiuzjef");
            u.setPassword("oizjfiozfj");
            u.setCne("oirefjirf");
            u.setAddress("poejfio");
            u.setFirstName("iouheruf");
            u.setLastName("iuhuf");


            List<Role> roles = new ArrayList<>();
            roles.add(role);
            u.setRoles(roles);

            userRepository.save(u);
        };
    }
}
