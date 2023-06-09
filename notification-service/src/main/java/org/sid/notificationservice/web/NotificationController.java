package org.sid.notificationservice.web;

import lombok.AllArgsConstructor;
import org.sid.notificationservice.dtos.NotificationDTO;
import org.sid.notificationservice.exceptions.NotificationNotFoundException;
import org.sid.notificationservice.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
@CrossOrigin("*")
public class NotificationController {
    private NotificationService notificationService;

    @GetMapping("")
    public List<NotificationDTO> getAllNotifications() {
        return notificationService.getNotifications();
    }

    @GetMapping("/{id}")
    public NotificationDTO getNotificationById(@PathVariable("id") Long id) throws NotificationNotFoundException {
       return notificationService.getNotificationById(id);
    }

    @GetMapping("/user/{id}")
    public List<NotificationDTO> getNotificationsByUser(@PathVariable("id") Long id){
        return notificationService.getNotificationsByUserId(id);
    }

    @PostMapping("")
    public NotificationDTO createNotification(@RequestBody NotificationDTO notification) {
        return notificationService.saveNotification(notification);
    }

    @PutMapping("/{id}")
    public NotificationDTO updateNotification(@PathVariable("id") Long id, @RequestBody NotificationDTO notification) throws NotificationNotFoundException {
        return notificationService.updateNotification(id,notification);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable("id") Long id) throws NotificationNotFoundException {
       notificationService.deleteNotification(id);
    }

    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<String> handleNotificationNotFoundException(NotificationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
