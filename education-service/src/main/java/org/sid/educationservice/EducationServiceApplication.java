package org.sid.educationservice;

import org.sid.educationservice.config.RsakeysConfig;
import org.sid.educationservice.entities.Continuing;
import org.sid.educationservice.entities.Departement;
import org.sid.educationservice.entities.Major;
import org.sid.educationservice.mappers.ContinuingMapperImpl;
import org.sid.educationservice.repositories.ContinuingRepository;
import org.sid.educationservice.repositories.DepartementRepository;
import org.sid.educationservice.repositories.MajorRepository;
import org.sid.educationservice.services.ContinuingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EnableEurekaClient
@SpringBootApplication
@EnableConfigurationProperties(RsakeysConfig.class)
public class EducationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ContinuingRepository continuingRepository,
                                               MajorRepository majorRepository,
                                               DepartementRepository departementRepository) {
        return args -> {
            // Continuing Education 1
            Continuing continuing1 = new Continuing();
            continuing1.setDuration(2);
            continuing1.setEducation_price(Long.valueOf(25000));
            continuing1.setDescription("BDCC education");
            continuing1.setDiploma("BDCC master");
            continuing1.setStart_date(new Date(9534425));
            continuingRepository.save(continuing1);

            // Continuing Education 2
            Continuing continuing2 = new Continuing();
            continuing2.setDuration(2);
            continuing2.setEducation_price(Long.valueOf(30000));
            continuing2.setDescription("AI education");
            continuing2.setDiploma("AI master");
            continuing2.setStart_date(new Date(9764425));
            continuingRepository.save(continuing2);

            // Department 1
            Departement departement1 = new Departement();
            departement1.setName("MATH INFO");
            departement1.setIntitule("Mathematique informatique");
            departementRepository.save(departement1);

            // Department 2
            Departement departement2 = new Departement();
            departement2.setName("AI");
            departement2.setIntitule("Artificial Intelligence");
            departementRepository.save(departement2);

            // Major 1 - Department 1
            Major major1 = new Major();
            major1.setName("GLSID");
            major1.setHeadOfDepartment(3L);
            major1.setDepartement(departement1);
            major1.setEducation(continuing1);
            majorRepository.save(major1);

            // Major 2 - Department 1
            Major major2 = new Major();
            major2.setName("SIAD");
            major2.setHeadOfDepartment(4L);
            major2.setDepartement(departement1);
            major2.setEducation(continuing1);
            majorRepository.save(major2);

            // Major 3 - Department 2
            Major major3 = new Major();
            major3.setName("AI Engineering");
            major3.setHeadOfDepartment(5L);
            major3.setDepartement(departement2);
            major3.setEducation(continuing2);
            majorRepository.save(major3);

            // Major 4 - Department 2
            Major major4 = new Major();
            major4.setName("AI Research");
            major4.setHeadOfDepartment(6L);
            major4.setDepartement(departement2);
            major4.setEducation(continuing2);
            majorRepository.save(major4);
        };
    }
}
