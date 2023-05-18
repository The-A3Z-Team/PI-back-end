package org.sid.notificationservice;

import org.sid.notificationservice.controllers.NotificationController;
import org.sid.notificationservice.entity.Notification;
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
			Notification notification=new Notification();
			notification.setDate(new Date());
			notification.setMessage("hello world");
			notification.setIsRead(false);
			notificationController.createNotification(notification);
		};
	}
}
