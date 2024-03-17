package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    @Column
    private String message;

    @Column
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public Notification(Integer notificationId, String message, User user, String type) {
        super();
        this.notificationId = notificationId;
        this.message = message;
        this.user = user;
        this.type = type;
    }

    public Notification() {
        super();
    }

    @Override
    public String toString() {
        return "Notification [notificationId=" + notificationId + ", message=" + message + "]" + ", type=" + type;
    }
    
}
