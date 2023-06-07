package org.sid.educationservice;

import org.sid.educationservice.web.ContinuingEducationController;
import org.sid.educationservice.entities.ContinuingEducation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class EducationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ContinuingEducationController continuingEducationController) {
        return args -> {
            ContinuingEducation continuing=new ContinuingEducation();
            continuing.setDescription("iuzehfiuzehfiuj");
            continuing.setDiplome("iouzhfiuhziuefhu");
            continuing.setDuree(897);
            continuingEducationController.saveContinuingEducation(continuing);
        };
    }
}
