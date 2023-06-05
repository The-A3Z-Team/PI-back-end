package org.sid.negociationservice;

import org.sid.negociationservice.entity.Negociation;
import org.sid.negociationservice.repositorys.NegociationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@EnableEurekaClient
@SpringBootApplication
public class NegociationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NegociationServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(NegociationRepository negociationRepository) {
		return args -> {
			Negociation negociation=new Negociation();
			negociation.setDate(new Date());
			negociation.setObjet("iuuifhziuefh");
			negociationRepository.save(negociation);
		};
	}
}
