package org.sid.notificationservice.services;

import org.sid.notificationservice.dtos.NotificationDTO;
import org.sid.notificationservice.exceptions.NotificationNotFoundException;

import java.util.List;

public interface NotificationService {
    NotificationDTO saveNotification(NotificationDTO notificationDTO);
    NotificationDTO updateNotification(Long id,NotificationDTO notificationDTO) throws NotificationNotFoundException;
    List<NotificationDTO> getNotifications();
    NotificationDTO getNotificationById(Long id) throws NotificationNotFoundException;

    List<NotificationDTO> getNotificationsByUserId(Long id);

    void deleteNotification(Long id) throws NotificationNotFoundException;
}
