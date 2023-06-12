package org.sid.educationservice;

import org.sid.educationservice.config.RsakeysConfig;
import org.sid.educationservice.entities.Continuing;
import org.sid.educationservice.entities.Major;
import org.sid.educationservice.mappers.ContinuingMapperImpl;
import org.sid.educationservice.repositories.ContinuingRepository;
import org.sid.educationservice.repositories.MajorRepository;
import org.sid.educationservice.services.ContinuingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@EnableEurekaClient
@SpringBootApplication
@EnableConfigurationProperties(RsakeysConfig.class)
public class EducationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ContinuingRepository continuingRepository,
                                               MajorRepository majorRepository) {
        return args -> {
            Continuing continuing=new Continuing();
            continuing.setDuration(2);
            continuing.setEducation_price(Long.valueOf(25000));
            continuing.setDescription("BDCC education");
            continuing.setDiploma("BDCC master");
            continuing.setStart_date(new Date(9534425));
            continuingRepository.save(continuing);

            Major major=new Major();
            major.setName("GLSID");
            major.setHeadOfDepartementId(Long.valueOf(3));
            majorRepository.save(major);
        };
    }
}
