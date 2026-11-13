package com.learning.notifyflow.service;

import com.learning.notifyflow.dto.CreateNotificationRequest;
import com.learning.notifyflow.model.Notification;
import com.learning.notifyflow.model.NotificationStatus;
import com.learning.notifyflow.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public Notification createNotification(CreateNotificationRequest request) {
        String key = UUID.randomUUID().toString();
        Notification notification = new Notification(key, request.recipient(), request.message(), NotificationStatus.PENDING);
        return notificationRepository.save(notification);
    }

    public Notification getNotificationById(String id) {
        return notificationRepository.findById(id).orElse(null);
    }

}
