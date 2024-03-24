package com.example.Controller;

import com.example.Service.UserService;
import com.example.model.User;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class represents the controller for managing user-related operations.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves the user profile based on the provided user ID.
     *
     * @param userId the ID of the user
     * @return a ResponseEntity containing the user profile if found, or
     *  HttpStatus.NOT_FOUND if the user is not found
     *  a ResponseEntity containing the user profile if found, or HttpStatus.NOT_FOUND if the user is not found
     */
    @GetMapping("/profiles")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getUserProfile(@RequestParam("userId") Integer userId) {
        Map<String, Object> user = userService.getUserInfo(userId);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates the specified fields of the user profile.
     *
     * @param userId      the ID of the user
     * @param updatedUser the updated user object containing the fields to be
     *                    updated
     * @return HttpStatus.OK if the user exists and the profile is updated, or
     *         HttpStatus.NOT_FOUND if the user is not found
     */
    @PatchMapping("/profiles")
    public HttpStatus updateProfileFields(@RequestBody Map<String, Object> updatedUser) {
        Integer userId = 1;
        String fullName = (String) updatedUser.get("fullName");
        String username = (String) updatedUser.get("username");
        String number = (String) updatedUser.get("number");
        String specialization = (String) updatedUser.get("specialization");
     // Convert dateOfBirth String to LocalDate object
        LocalDate dateOfBirth = LocalDate.parse((String) updatedUser.get("dateOfBirth"));


        User existingUser = userService.getUserById(userId);
        if (existingUser != null) {
            userService.updateUserProfileFields(existingUser, fullName, username, number, specialization, dateOfBirth);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

}