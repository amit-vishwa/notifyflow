package com.learning.notifyflow.controller;

import com.learning.notifyflow.dto.CreateNotificationRequest;
import com.learning.notifyflow.dto.CreateNotificationResponse;
import com.learning.notifyflow.dto.NotificationResponse;
import com.learning.notifyflow.model.Notification;
import com.learning.notifyflow.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<CreateNotificationResponse> createNotification(@Valid @RequestBody CreateNotificationRequest request) {
        Notification notification = notificationService.createNotification(request);
        CreateNotificationResponse response = new CreateNotificationResponse(notification.getId(), notification.getStatus());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable("id") String id) {
        Notification notification = notificationService.getNotificationById(id);
        if (notification == null) {
            return ResponseEntity.notFound().build();
        }
        NotificationResponse response = new NotificationResponse(notification.getId(), notification.getRecipient(), notification.getMessage(), notification.getStatus());
        return ResponseEntity.ok(response);
    }

}
