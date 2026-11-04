package com.learning.notifyflow.dto;

import com.learning.notifyflow.model.NotificationStatus;

public record CreateNotificationResponse(String id, NotificationStatus status) {
}
