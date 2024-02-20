package com.example.Controller;

import com.example.Service.UserService;
import com.example.model.User;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class represents the controller for handling user-related operations.
 * It provides endpoints for retrieving, updating, and patching user profiles.
 */
@RestController
@RequestMapping("/api/profiles")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves the user profile for the given user ID.
     *
     * @param userId the ID of the user
     * @return the user profile if found, or a 404 Not Found response
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserProfile(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates the user profile for the given user ID.
     *
     * @param userId      the ID of the user
     * @param updatedUser the updated user profile
     * @return the updated user profile if found, or a 404 Not Found response
     */
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserProfile(@PathVariable Integer userId,
                                                  @RequestBody User updatedUser) {
        User existingUser = userService.getUserById(userId);
        if (existingUser != null) {
            updatedUser.setUserid(userId);
            User savedUser = userService.updateUserProfile(updatedUser);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates specific fields of the user profile for the given user ID.
     *
     * @param userId         the ID of the user
     * @param firstName      the updated first name
     * @param lastName       the updated last name
     * @param username       the updated username
     * @param number         the updated number
     * @param specialization the updated specialization
     * @param dateOfBirth    the updated date of birth
     * @return the updated user profile if found, or a 404 Not Found response
     */
    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateProfileFields(@PathVariable Integer userId,
                                                    @RequestParam String firstName,
                                                    @RequestParam String lastName,
                                                    @RequestParam String username,
                                                    @RequestParam String number,
                                                    @RequestParam String specialization,
                                                    @RequestParam Date dateOfBirth) {
        userService.updateUserProfileFields(userId, firstName, lastName, username, number, specialization, dateOfBirth);
        User updatedUser = userService.getUserById(userId);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}