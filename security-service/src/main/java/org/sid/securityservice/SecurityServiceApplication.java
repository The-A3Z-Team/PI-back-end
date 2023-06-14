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
    CommandLineRunner start(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            // Create roles
            Role studentRole = new Role(null, ERole.STUDENT);
            Role hodRole = new Role(null, ERole.HEAD_OF_DEPARTEMENT);
            Role itManagerRole = new Role(null, ERole.IT_MANAGER);
            Role financialOfficerRole = new Role(null, ERole.FINANCIAL_OFFICIER);
            Role generalDirectorRole = new Role(null, ERole.GENERAL_DIRECTOR);
            Role deputyManagerRole = new Role(null, ERole.DEPUTY_MANAGER);

            roleRepository.save(studentRole);
            roleRepository.save(hodRole);
            roleRepository.save(itManagerRole);
            roleRepository.save(financialOfficerRole);
            roleRepository.save(generalDirectorRole);
            roleRepository.save(deputyManagerRole);

            // Students
            User student1 = new User();
            student1.setEmail("student1@gmail.com");
            student1.setUsername("student1");
            student1.setRoles(Set.of(studentRole));
            student1.setAdresse("CASABLANCA");
            student1.setCni("BJ111111");
            student1.setGender(Gender.MALE);
            student1.setFirstName("Student1");
            student1.setLastName("Lastname1");
            student1.setCne("R111111");
            student1.setDateNaissance(new Date(2000, 1, 1));
            student1.setPassword(new PasswordEncoding().getEncodedPassword("student1@"));
            student1.setIdMajorOfStudent(3L);
            student1.setIdEducationOfStudent(1L);
            student1.setIdHeadOfDepartementManagerOfStudent(4L);

            User student2 = new User();
            student2.setEmail("student2@gmail.com");
            student2.setUsername("student2");
            student2.setRoles(Set.of(studentRole));
            student2.setAdresse("CASABLANCA");
            student2.setCni("BJ222222");
            student2.setGender(Gender.FEMALE);
            student2.setFirstName("Student2");
            student2.setLastName("Lastname2");
            student2.setCne("R222222");
            student2.setDateNaissance(new Date(2000, 2, 2));
            student2.setPassword(new PasswordEncoding().getEncodedPassword("student2@"));
            student2.setIdMajorOfStudent(1L);
            student2.setIdEducationOfStudent(1L);
            student2.setIdHeadOfDepartementManagerOfStudent(4L);

            User student3 = new User();
            student3.setEmail("student3@gmail.com");
            student3.setUsername("student3");
            student3.setRoles(Set.of(studentRole));
            student3.setAdresse("CASABLANCA");
            student3.setCni("BJ333333");
            student3.setGender(Gender.MALE);
            student3.setFirstName("Student3");
            student3.setLastName("Lastname3");
            student3.setCne("R333333");
            student3.setDateNaissance(new Date(2000, 3, 3));
            student3.setPassword(new PasswordEncoding().getEncodedPassword("student3@"));
            student3.setIdMajorOfStudent(1L);
            student3.setIdEducationOfStudent(1L);
            student3.setIdHeadOfDepartementManagerOfStudent(4L);

            // Heads of Department
            User hod1 = new User();
            hod1.setEmail("hod1@gmail.com");
            hod1.setUsername("hod1");
            hod1.setRoles(Set.of(hodRole));
            hod1.setAdresse("CASABLANCA");
            hod1.setCni("BJ444444");
            hod1.setGender(Gender.FEMALE);
            hod1.setFirstName("HOD1");
            hod1.setLastName("Lastname1");
            hod1.setCne("R444444");
            hod1.setDateNaissance(new Date(1980, 1, 1));
            hod1.setPassword(new PasswordEncoding().getEncodedPassword("hod1@"));
            hod1.setIdMajorOfHeadOfDepartement(1L);

            User hod2 = new User();
            hod2.setEmail("hod2@gmail.com");
            hod2.setUsername("hod2");
            hod2.setRoles(Set.of(hodRole));
            hod2.setAdresse("CASABLANCA");
            hod2.setCni("BJ555555");
            hod2.setGender(Gender.MALE);
            hod2.setFirstName("HOD2");
            hod2.setLastName("Lastname2");
            hod2.setCne("R555555");
            hod2.setDateNaissance(new Date(1980, 2, 2));
            hod2.setPassword(new PasswordEncoding().getEncodedPassword("hod2@"));
            hod2.setIdMajorOfHeadOfDepartement(1L);

            User hod3 = new User();
            hod3.setEmail("hod3@gmail.com");
            hod3.setUsername("hod3");
            hod3.setRoles(Set.of(hodRole));
            hod3.setAdresse("CASABLANCA");
            hod3.setCni("BJ666666");
            hod3.setGender(Gender.FEMALE);
            hod3.setFirstName("HOD3");
            hod3.setLastName("Lastname3");
            hod3.setCne("R666666");
            hod3.setDateNaissance(new Date(1980, 3, 3));
            hod3.setPassword(new PasswordEncoding().getEncodedPassword("hod3@"));
            hod3.setIdMajorOfHeadOfDepartement(1L);

            // IT Manager
            User itManager = new User();
            itManager.setEmail("itmanager@gmail.com");
            itManager.setUsername("itmanager");
            itManager.setRoles(Set.of(itManagerRole));
            itManager.setAdresse("CASABLANCA");
            itManager.setCni("BJ777777");
            itManager.setGender(Gender.MALE);
            itManager.setFirstName("IT");
            itManager.setLastName("Manager");
            itManager.setCne("R777777");
            itManager.setDateNaissance(new Date(1990, 5, 5));
            itManager.setPassword(new PasswordEncoding().getEncodedPassword("itmanager@"));

            // Official Manager
            User officialManager = new User();
            officialManager.setEmail("officialmanager@gmail.com");
            officialManager.setUsername("officialmanager");
            officialManager.setRoles(Set.of(financialOfficerRole));
            officialManager.setAdresse("CASABLANCA");
            officialManager.setCni("BJ888888");
            officialManager.setGender(Gender.FEMALE);
            officialManager.setFirstName("Official");
            officialManager.setLastName("Manager");
            officialManager.setCne("R888888");
            officialManager.setDateNaissance(new Date(1985, 4, 4));
            officialManager.setPassword(new PasswordEncoding().getEncodedPassword("officialmanager@"));

            // General Director
            User generalDirector = new User();
            generalDirector.setEmail("generaldirector@gmail.com");
            generalDirector.setUsername("generaldirector");
            generalDirector.setRoles(Set.of(generalDirectorRole));
            generalDirector.setAdresse("CASABLANCA");
            generalDirector.setCni("BJ999999");
            generalDirector.setGender(Gender.MALE);
            generalDirector.setFirstName("General");
            generalDirector.setLastName("Director");
            generalDirector.setCne("R999999");
            generalDirector.setDateNaissance(new Date(1975, 6, 6));
            generalDirector.setPassword(new PasswordEncoding().getEncodedPassword("generaldirector@"));

            // Deputy Manager
            User deputyManager = new User();
            deputyManager.setEmail("deputymanager@gmail.com");
            deputyManager.setUsername("deputymanager");
            deputyManager.setRoles(Set.of(deputyManagerRole));
            deputyManager.setAdresse("CASABLANCA");
            deputyManager.setCni("BJ101010");
            deputyManager.setGender(Gender.FEMALE);
            deputyManager.setFirstName("Deputy");
            deputyManager.setLastName("Manager");
            deputyManager.setCne("R101010");
            deputyManager.setDateNaissance(new Date(1995, 7, 7));
            deputyManager.setPassword(new PasswordEncoding().getEncodedPassword("deputymanager@"));

            userRepository.save(student1);
            userRepository.save(student2);
            userRepository.save(student3);
            userRepository.save(hod1);
            userRepository.save(hod2);
            userRepository.save(hod3);
            userRepository.save(itManager);
            userRepository.save(officialManager);
            userRepository.save(generalDirector);
            userRepository.save(deputyManager);
        };
    }
}

