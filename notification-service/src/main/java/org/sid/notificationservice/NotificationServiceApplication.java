package org.sid.notificationservice;

import org.sid.notificationservice.config.RsakeysConfig;
import org.sid.notificationservice.dtos.NotificationDTO;
import org.sid.notificationservice.web.NotificationController;
import org.sid.notificationservice.entities.Notification;
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
			notification.setUserId(Long.valueOf(1));
			notificationController.createNotification(notification);

			NotificationDTO notification2=new NotificationDTO();
			notification.setDate(new Date());
			notification.setMessage("hello world");
			notification.setIsRead(false);
			notification.setUserId(Long.valueOf(1));
			notificationController.createNotification(notification2);

			NotificationDTO notification3=new NotificationDTO();
			notification.setDate(new Date());
			notification.setMessage("hello world");
			notification.setIsRead(false);
			notification.setUserId(Long.valueOf(1));
			notificationController.createNotification(notification3);
		};
	}
}
