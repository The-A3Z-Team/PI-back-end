package org.sid.securityservice;

import org.sid.securityservice.config.RsakeysConfig;

import org.sid.securityservice.ennumeration.Gender;
import org.sid.securityservice.entities.ERole;
import org.sid.securityservice.entities.Role;
import org.sid.securityservice.entities.User;
import org.sid.securityservice.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@EnableEurekaClient
@SpringBootApplication
@EnableConfigurationProperties(RsakeysConfig.class)
public class SecurityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityServiceApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(
                            RoleRepository roleRepository,
                            UserRepository userRepository){
        return args -> {
            Role role = new Role();
            role.setName(ERole.STUDENT);
            roleRepository.save(role);

            Set<Role> roles = new HashSet<>();
            roles.add(role);

            User user = new User();
            user.setEmail("taffah@gmail.com");
            user.setUsername("taffah201");
            user.setRoles(roles);
            user.setAdresse("CASABLANCA");
            user.setCni("BJ473674");
            user.setGender(Gender.MALE);
            user.setFirstName("Achraf");
            user.setLastName("TAFFAH");
            user.setCne("R130419706");
            user.setDateNaissance(new Date(2001,9,7));

            String password = "1234";
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(password);

            user.setPassword(encodedPassword);

            userRepository.save(user);
        };
    }
}
