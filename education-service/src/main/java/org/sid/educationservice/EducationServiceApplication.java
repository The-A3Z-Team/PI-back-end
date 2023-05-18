package org.sid.educationservice;

import org.sid.educationservice.controllers.ContinuingEducationController;
import org.sid.educationservice.entity.Continuing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EducationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ContinuingEducationController continuingEducationController) {
        return args -> {
            Continuing continuing=new Continuing();
            continuing.setDescription("iuzehfiuzehfiuj");
            continuing.setDiplome("iouzhfiuhziuefhu");
            continuing.setDuree(897);
            continuingEducationController.saveContinuingEducation(continuing);
        };
    }
}
