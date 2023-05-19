package org.sid.notificationservice.repositories;

import org.sid.notificationservice.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    public List<Notification> getNotificationsByUserId(Long userId);
}
