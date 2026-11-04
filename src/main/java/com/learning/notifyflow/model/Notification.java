package com.learning.notifyflow.model;

public class Notification {

    private final String id;
    private final String recipient;
    private final String message;
    private NotificationStatus status;

    public Notification(String id, String recipient, String message, NotificationStatus status) {
        this.id = id;
        this.recipient = recipient;
        this.message = message;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }
}
