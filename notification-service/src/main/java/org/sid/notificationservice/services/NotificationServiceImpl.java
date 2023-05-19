package org.sid.notificationservice.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.notificationservice.dtos.NotificationDTO;
import org.sid.notificationservice.entities.Notification;
import org.sid.notificationservice.exceptions.NotificationNotFoundException;
import org.sid.notificationservice.mappers.NotificationMapperImpl;
import org.sid.notificationservice.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService{
    private NotificationRepository notificationRepository;
    private NotificationMapperImpl notificationMapper;

    @Override
    public NotificationDTO saveNotification(NotificationDTO notificationDTO) {
        log.info("Saving new notification");
        Notification notification = notificationMapper.fromNotificationDTO(notificationDTO);
        Notification savedNotification = notificationRepository.save(notification);
        return notificationMapper.fromNotification(savedNotification);
    }

    public NotificationDTO updateNotification(Long id, NotificationDTO notificationDTO) throws NotificationNotFoundException {
        log.info("Updating notification");

        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isEmpty()) {
            throw new NotificationNotFoundException("Notification not found");
        }

        Notification existingNotification = optionalNotification.get();

        existingNotification.setObjet(notificationDTO.getObjet());
        existingNotification.setMessage(notificationDTO.getMessage());

        Notification updatedNotification = notificationRepository.save(existingNotification);

        return notificationMapper.fromNotification(updatedNotification);
    }



    @Override
    public List<NotificationDTO> getNotifications() {
        log.info("Fetching all Notifications");
        List<Notification> notifications = notificationRepository.findAll();
        return notificationMapper.toNotificationDTOs(notifications);
    }

    @Override
    public NotificationDTO getNotificationById(Long id) throws NotificationNotFoundException {
        log.info("Fetching User with ID: {}", id);
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("User not found with ID: " + id));
        return notificationMapper.fromNotification(notification);
    }

    @Override
    public List<NotificationDTO> getNotificationsByUserId(Long id){
        log.info("Fetching User with ID: {}", id);
        List<Notification> notifications = notificationRepository.getNotificationsByUserId(id);
        return notificationMapper.toNotificationDTOs(notifications);
    }

    @Override
    public void deleteNotification(Long id) throws NotificationNotFoundException {
        log.info("Deleting User with ID: {}", id);
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("User not found with ID: " + id));
        notificationRepository.delete(notification);
    }
}
