package com.example.Controller;

import com.example.Service.UserService;
import com.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserProfile() {
        // Mock data
        int userId = 1;
        User user = new User();
        user.setUserid(userId);
        // Mock service behavior
        when(userService.getUserById(userId)).thenReturn(user);

        // Call controller method
        ResponseEntity<User> response = userController.getUserProfile(userId);

        // Verify service method is called
        verify(userService, times(1)).getUserById(userId);

        // Assert response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testUpdateUserProfile() {
        // Mock data
        int userId = 1;
        User existingUser = new User();
        existingUser.setUserid(userId);
        User updatedUser = new User();
        updatedUser.setUserid(userId);
        // Mock service behavior
        when(userService.getUserById(userId)).thenReturn(existingUser);
        when(userService.updateUserProfile(updatedUser)).thenReturn(updatedUser);

        // Call controller method
        ResponseEntity<User> response = userController.updateUserProfile(userId, updatedUser);

        // Verify service methods are called
        verify(userService, times(1)).getUserById(userId);
        verify(userService, times(1)).updateUserProfile(updatedUser);

        // Assert response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testUpdateProfileFields() {
        // Mock data
        int userId = 1;
        String firstName = "John";
        String lastName = "Doe";
        String username = "johndoe";
        String number = "1234567890";
        String specialization = "Software Engineering";
        Date dateOfBirth = new Date(2000, 1, 1);

        // Call controller method
        ResponseEntity<User> response = userController.updateProfileFields(userId, firstName, lastName, username, number, specialization, dateOfBirth);

        // Verify service method is called
        verify(userService, times(1)).updateUserProfileFields(userId, firstName, lastName, username, number, specialization, dateOfBirth);

        // Assert response
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
