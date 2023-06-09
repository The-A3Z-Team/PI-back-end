package org.sid.notificationservice.mappers;

import org.sid.notificationservice.dtos.NotificationDTO;
import org.sid.notificationservice.entities.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationMapper {
    NotificationDTO fromNotification(Notification notification);
    Notification fromNotificationDTO(NotificationDTO userDTO);
    List<NotificationDTO> toNotificationDTOs(List<Notification> notifications);
}
