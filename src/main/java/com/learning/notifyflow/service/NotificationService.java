package com.learning.notifyflow.service;

import com.learning.notifyflow.dto.CreateNotificationRequest;
import com.learning.notifyflow.model.Notification;
import com.learning.notifyflow.model.NotificationStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NotificationService {

    private final ConcurrentHashMap<String, Notification> notifications;

    public NotificationService() {
        notifications = new ConcurrentHashMap<>();
    }

    public Notification createNotification(CreateNotificationRequest request) {
        String key = UUID.randomUUID().toString();
        Notification notification = new Notification(key, request.recipient(), request.message(), NotificationStatus.PENDING);
        notifications.put(key, notification);
        return notification;
    }

    public Notification getNotificationById(String id) {
        return notifications.get(id);
    }

}
