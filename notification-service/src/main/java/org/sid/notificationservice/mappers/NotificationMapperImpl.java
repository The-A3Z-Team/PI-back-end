package org.sid.notificationservice.mappers;

import org.sid.notificationservice.dtos.NotificationDTO;
import org.sid.notificationservice.entities.Notification;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationMapperImpl {
    public NotificationDTO fromNotification(Notification notification){
        NotificationDTO notificationDTO=new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        return  notificationDTO;
    }

    public Notification fromNotificationDTO(NotificationDTO userDTO){
        Notification notification=new Notification();
        BeanUtils.copyProperties(userDTO,notification);
        return  notification;
    }

    public List<NotificationDTO> toNotificationDTOs(List<Notification> notifications) {
        return notifications.stream()
                .map(this::fromNotification)
                .collect(Collectors.toList());
    }
}
