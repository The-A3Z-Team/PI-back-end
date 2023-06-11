package org.sid.securityservice;

import org.sid.securityservice.config.PasswordEncoding;
import org.sid.securityservice.config.RsakeysConfig;

import org.sid.securityservice.ennumeration.ERole;
import org.sid.securityservice.ennumeration.Gender;
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
            // La création des roles*/

            Role role1 = new Role(null, ERole.STUDENT);
            Role role2 = new Role(null, ERole.FINANCIAL_OFFICIER);
            Role role3 = new Role(null, ERole.HEAD_OF_DEPARTEMENT);
            Role role4 = new Role(null, ERole.SCHOOLING);
            Role role5 = new Role(null, ERole.DEPUTY_MANAGER);
            Role role6 = new Role(null, ERole.IT_MANAGER);
            Role role7 = new Role(null, ERole.GENERAL_DIRECTOR);

            roleRepository.save(role1);
            roleRepository.save(role2);
            roleRepository.save(role3);
            roleRepository.save(role4);
            roleRepository.save(role5);
            roleRepository.save(role6);
            roleRepository.save(role7);

            Set<Role> achrafRoles = new HashSet<>();
            achrafRoles.add(role1);

            Set<Role> abirRoles = new HashSet<>();
            abirRoles.add(role2);

            Set<Role> abdelmalekRoles = new HashSet<>();
            abdelmalekRoles.add(role3);

            Set<Role> salahRoles = new HashSet<>();
            salahRoles.add(role4);

            Set<Role> zakiaRoles = new HashSet<>();
            zakiaRoles.add(role5);

            Set<Role> itRoles = new HashSet<>();
            itRoles.add(role6);

            Set<Role> adgdirectorRoles = new HashSet<>();
            adgdirectorRoles.add(role7);

            // La création des utilisateurs

            User user1 = new User();
            user1.setEmail("taffah@gmail.com");
            user1.setUsername("taffah201");
            user1.setRoles(achrafRoles);
            user1.setAdresse("CASABLANCA");
            user1.setCni("BJ473674");
            user1.setGender(Gender.MALE);
            user1.setFirstName("Achraf");
            user1.setLastName("TAFFAH");
            user1.setCne("R130419706");
            user1.setDateNaissance(new Date(2001,9,7));
            user1.setPassword(new PasswordEncoding().getEncodedPassword("achraf1234@"));

            User user2 = new User();
            user2.setEmail("abir@gmail.com");
            user2.setUsername("abir1234");
            user2.setRoles(abirRoles);
            user2.setAdresse("CASABLANCA");
            user2.setCni("BJ98744");
            user2.setGender(Gender.FEMALE);
            user2.setFirstName("Abir");
            user2.setLastName("Laarousi");
            user2.setCne("R147777");
            user2.setDateNaissance(new Date(2001,10,7));
            user2.setPassword(new PasswordEncoding().getEncodedPassword("abir@"));

            User user3 = new User();
            user3.setEmail("abdelmalek@gmail.com");
            user3.setUsername("abdelmalek1234");
            user3.setRoles(abdelmalekRoles);
            user3.setAdresse("CASABLANCA");
            user3.setCni("BJ98744");
            user3.setGender(Gender.MALE);
            user3.setFirstName("abdelmalek");
            user3.setLastName("ennani");
            user3.setCne("R777999");
            user3.setDateNaissance(new Date(2000,10,7));
            user3.setPassword(new PasswordEncoding().getEncodedPassword("abdelmalek@"));

            User user4 = new User();
            user4.setEmail("salah@gmail.com");
            user4.setUsername("salah1234");
            user4.setRoles(salahRoles);
            user4.setAdresse("CASABLANCA");
            user4.setCni("BJ98744");
            user4.setGender(Gender.MALE);
            user4.setFirstName("salah");
            user4.setLastName("lkouli");
            user4.setCne("R888999");
            user4.setDateNaissance(new Date(2000,10,7));
            user4.setPassword(new PasswordEncoding().getEncodedPassword("salah@@"));

            User user5 = new User();
            user5.setEmail("zakia@gmail.com");
            user5.setUsername("zakia1234");
            user5.setRoles(zakiaRoles);
            user5.setAdresse("CASABLANCA");
            user5.setCni("BJ98744");
            user5.setGender(Gender.FEMALE);
            user5.setFirstName("zakia");
            user5.setLastName("regoug");
            user5.setCne("R412458");
            user5.setDateNaissance(new Date(2002,10,7));
            user5.setPassword(new PasswordEncoding().getEncodedPassword("zakia@@"));

            User user6 = new User();
            user6.setEmail("it@gmail.com");
            user6.setUsername("it");
            user6.setRoles(itRoles);
            user6.setAdresse("CASABLANCA");
            user6.setCni("BJ98744");
            user6.setGender(Gender.FEMALE);
            user6.setFirstName("it");
            user6.setLastName("it");
            user6.setDateNaissance(new Date(1980,10,7));
            user6.setPassword(new PasswordEncoding().getEncodedPassword("it1234@@"));

            User user7 = new User();
            user7.setEmail("adgdirector@gmail.com");
            user7.setUsername("adgdirector1234");
            user7.setRoles(adgdirectorRoles);
            user7.setAdresse("CASABLANCA");
            user7.setCni("BJ741852");
            user7.setGender(Gender.FEMALE);
            user7.setFirstName("adgdirector");
            user7.setLastName("adgdirector");
            user7.setDateNaissance(new Date(1977,10,7));
            user7.setPassword(new PasswordEncoding().getEncodedPassword("adgdirector@@"));

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);
            userRepository.save(user5);
            userRepository.save(user6);
            userRepository.save(user7);
        };
    }
}
