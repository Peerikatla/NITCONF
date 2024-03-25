package com.example.Controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Controller.UserController;
import com.example.Service.UserService;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void getUserProfile_UserExists_ReturnsUserProfile() {
        // Mock behavior of UserService
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("userId", 1);
        userMap.put("fullName", "vijayalakshmig");
        userMap.put("username", "lucky");
        userMap.put("number", "9391829041");
        userMap.put("specialization", "Computer Science");
        userMap.put("dateOfBirth", LocalDate.of(2004, Month.JANUARY, 1).toString());
        userMap.put("email", "vijayalakshi3975@gmail.com");
        when(userService.getUserInfo(anyInt())).thenReturn(userMap);

        // Test
        ResponseEntity<Map<String, Object>> response = userController.getUserProfile(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        Map<String, Object> userProfile = response.getBody();
        
        assertNotNull(userProfile);
        assertEquals(1, userProfile.get("userId"));
        assertEquals("vijayalakshmig", userProfile.get("fullName"));
        assertEquals("lucky", userProfile.get("username"));
        assertEquals("9391829041", userProfile.get("number"));
        assertEquals("Computer Science", userProfile.get("specialization"));
        assertEquals(LocalDate.of(2004, Month.JANUARY, 1).toString(), userProfile.get("dateOfBirth"));
        assertEquals("vijayalakshi3975@gmail.com", userProfile.get("email"));
    }

    @Test
    public void getUserProfile_UserDoesNotExist_ReturnsNotFound() {
        // Mock behavior of UserService returning null
        when(userService.getUserInfo(anyInt())).thenReturn(null);

        // Test
        ResponseEntity<Map<String, Object>> response = userController.getUserProfile(2);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}