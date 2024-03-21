package com.example.Controller;

import com.example.Service.UserService;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class represents the controller for handling user-related API requests.
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
     * @return a ResponseEntity containing the user profile if found, or HttpStatus.NOT_FOUND if not found
     */
    @GetMapping("/profiles")
    @ResponseBody
    public ResponseEntity<User> getUserProfile(@RequestParam("userId") Integer userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            System.out.println("in api" + user.toString());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            System.out.println("User not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates the specified fields of the user profile.
     *
     * @param userId      the ID of the user
     * @param updatedUser the updated user object containing the fields to be updated
     * @return HttpStatus.OK if the user exists and the profile is updated, or HttpStatus.NOT_FOUND if the user is not found
     */
    @PatchMapping("/profiles")
    @ResponseBody
    public HttpStatus updateProfileFields(@RequestParam("userId") Integer userId, @RequestBody User updatedUser) {
        User existingUser = userService.getUserById(userId);
        if (existingUser != null) {
            userService.updateUserProfileFields(existingUser, updatedUser);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
