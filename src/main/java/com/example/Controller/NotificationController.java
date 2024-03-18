package com.example.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.Service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;
/**
 * This class represents the controller for handling notifications.
 */
@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * Retrieves all notifications for a given user.
     *
     * @param userId the ID of the user
     * @return a ResponseEntity containing a list of notifications
     */
    @GetMapping("/notifications")
    public ResponseEntity<List<Map<String, Object>>> getNotifications(@RequestParam Integer userId) {
        List<Map<String, Object>> notifications = notificationService.getAllNotification(userId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
    
}
