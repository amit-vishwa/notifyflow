package com.learning.notifyflow.dto;

import com.learning.notifyflow.model.NotificationStatus;

public record NotificationResponse(String id, String recipient, String message, NotificationStatus status) {
}
