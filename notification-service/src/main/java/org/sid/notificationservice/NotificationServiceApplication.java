package org.sid.notificationservice;

import org.sid.notificationservice.dtos.NotificationDTO;
import org.sid.notificationservice.web.NotificationController;
import org.sid.notificationservice.entities.Notification;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(NotificationController notificationController) {
		return args -> {
			NotificationDTO notification=new NotificationDTO();
			notification.setDate(new Date());
			notification.setMessage("hello world");
			notification.setIsRead(false);
			notification.setUserId(Long.valueOf(3));
			notificationController.createNotification(notification);
		};
	}
}
