package org.sid.paymentservice;

import org.sid.paymentservice.config.RsakeysConfig;
import org.sid.paymentservice.ennumerations.PaymentProcess;
import org.sid.paymentservice.entities.*;
import org.sid.paymentservice.services.CashService;
import org.sid.paymentservice.services.ChequeService;
import org.sid.paymentservice.services.TransferService;
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
public class PaymentServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(TransferService transferService,
											   CashService cashService,
											   ChequeService chequeService) {
		return args -> {
			Transfer transfer=new Transfer();
			transfer.setDate(new Date());
			transfer.setMontant(8945);
			transfer.setIdStudent(Long.valueOf(1));
			transfer.setIdContinuingEducation(Long.valueOf(1));
			transfer.setPaymentProcess(PaymentProcess.NORMAL);
			transferService.saveTransfer(transfer);

			Cash cash=new Cash();
			cash.setDate(new Date());
			cash.setMontant(897546);
			cash.setIdStudent(Long.valueOf(1));
			cash.setIdContinuingEducation(Long.valueOf(1));
			cash.setPaymentProcess(PaymentProcess.NORMAL);
			cashService.saveCash(cash);

			Cheque cheque=new Cheque();
			cheque.setDate(new Date());
			cheque.setMontant(8745);
			cheque.setIdStudent(Long.valueOf(1));
			cheque.setIdContinuingEducation(Long.valueOf(1));
			cheque.setPaymentProcess(PaymentProcess.NORMAL);
			chequeService.saveCheque(cheque);
		};
	}
}
