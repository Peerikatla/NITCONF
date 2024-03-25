package com.example.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import com.example.Service.UserService;
import com.example.model.User;

public class EditProfileControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void updateProfileFields_UserExists_ReturnsOk() {
        // Arrange
        int userId = 1;
        User existingUser = new User();
        existingUser.setUserid(userId);
        // Mock the behavior of userService.getUserById(userId) to return the existing user
        when(userService.getUserById(userId)).thenReturn(existingUser);

        // Create the updated user details
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", userId); // Include the userId in the request body
        requestBody.put("fullName", "vijjj");
        requestBody.put("username", "vijj123");
        requestBody.put("number", "1234567890");
        requestBody.put("specialization", "Data Science");
        requestBody.put("dateOfBirth", LocalDate.of(2003, 5, 28).toString());

        // Act
        HttpStatus status = userController.updateProfileFields(requestBody);

        // Assert
        assertEquals(HttpStatus.OK, status);
    }

    @Test
    void updateProfileFields_UserDoesNotExist_ReturnsNotFound() {
        // Arrange
        int userId = 1;
        // Mock the behavior of userService.getUserById(userId) to return null, indicating that the user does not exist
        when(userService.getUserById(userId)).thenReturn(null);

        // Create the updated user details
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", userId); // Include the userId in the request body
        requestBody.put("fullName", "vijjj");
        requestBody.put("username", "vij123");
        requestBody.put("number", "1234567890");
        requestBody.put("specialization", "Data Science");
        requestBody.put("dateOfBirth", LocalDate.of(2003, 5, 28).toString());

        // Act
        HttpStatus status = userController.updateProfileFields(requestBody);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, status);
    }
}