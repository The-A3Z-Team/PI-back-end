package org.sid.paymentservice;

import org.sid.paymentservice.config.RsakeysConfig;
import org.sid.paymentservice.entity.*;
import org.sid.paymentservice.repositorys.PaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;



@EnableEurekaClient
@SpringBootApplication
@RestController
@EnableConfigurationProperties(RsakeysConfig.class)
public class PaymentServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(PaymentRepository paymentRepository) {
		return args -> {
			Transfer transfer=new Transfer();
			transfer.setDate(new Date());
			transfer.setMontant(8945);
			paymentRepository.save(transfer);

			Cash cash=new Cash();
			cash.setDate(new Date());
			cash.setMontant(98745);
			paymentRepository.save(cash);

			Cheque cheque=new Cheque();
			cheque.setDate(new Date());
			cheque.setMontant(8745);
			paymentRepository.save(cheque);
		};
	}
}
