package com.example.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.NotificationRepository;
import com.example.model.Notification;


@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Map<String, Object>> getAllNotification(Integer userId) {
        List<Notification> notifications = notificationRepository.findNotificationsByUserId(userId);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Notification notification : notifications) {
            Map<String, Object> notificationMap = new HashMap<>();
            notificationMap.put("notificationId", notification.getNotificationId());
            notificationMap.put("message", notification.getMessage());
            notificationMap.put("type", notification.getType());
            result.add(notificationMap);
        }
        return result;
    }
}
