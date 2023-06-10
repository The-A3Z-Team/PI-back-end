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
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        List<NotificationDTO> notificationDTOS =notificationService.getNotifications();
        return ResponseEntity.ok(notificationDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable("id") Long id) throws NotificationNotFoundException {
        NotificationDTO notificationDTO = notificationService.getNotificationById(id);
        if(notificationDTO != null){
            return ResponseEntity.ok(notificationDTO);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByUser(@PathVariable("id") Long id){
        List<NotificationDTO> notificationDTOS = notificationService.getNotificationsByUserId(id);
        return ResponseEntity.ok(notificationDTOS);
    }

    @PostMapping("")
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notification) {
        NotificationDTO notificationDTO = notificationService.saveNotification(notification);
        return new ResponseEntity<>(notificationDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDTO> updateNotification(@PathVariable("id") Long id, @RequestBody NotificationDTO notification) throws NotificationNotFoundException {
        NotificationDTO updatedNotificationDTO = notificationService.updateNotification(id,notification);
        if(updatedNotificationDTO != null){
            return new ResponseEntity<>(updatedNotificationDTO, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNotification(@PathVariable("id") Long id) throws NotificationNotFoundException {
        notificationService.deleteNotification(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<String> handleNotificationNotFoundException(NotificationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
