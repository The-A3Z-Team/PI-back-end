package org.sid.negociationservice;

import org.sid.negociationservice.config.RsakeysConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
@EnableConfigurationProperties(RsakeysConfig.class)
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
