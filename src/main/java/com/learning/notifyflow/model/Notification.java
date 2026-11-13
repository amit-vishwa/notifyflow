package com.learning.notifyflow.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @Column(nullable = false, length = 36)
    private String id;

    @Column(nullable = false, length = 254)
    private String recipient;

    @Column(nullable = false, length = 500)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NotificationStatus status;

    protected Notification() {

    }

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
