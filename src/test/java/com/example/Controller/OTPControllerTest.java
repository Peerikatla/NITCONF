package com.example.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Repository.UserRepository;
import com.example.model.User;

public class OTPControllerTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    OTPController otpController;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserid(1);
        user.setEmail("gayatripeerikatla@gmail.com");
        user.setFullName("Gayatri P");
        user.setUsername("gayatri");
        user.setNumber("8688469868");
        user.setPassword("me@1");
        user.setSpecialization("Machine learning");
        user.setDateOfBirth(LocalDate.of(2003, Month.SEPTEMBER, 8));
    }

    @Test
    void testResetPassword() {
        // Create a mock user
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("oldPassword");

        // Mock the userRepository.findByEmail method
        when(userRepository.findByEmail(anyString())).thenReturn(user);

        // Call the resetPassword method
        ResponseEntity<String> response = otpController.resetPassword("test@example.com", "newPassword");

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Password reset successfully.", response.getBody());
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    void testResetPassword_UserNotFound() {
        // Mock the userRepository.findByEmail method to return null
        when(userRepository.findByEmail(anyString())).thenReturn(null);

        // Call the resetPassword method
        ResponseEntity<String> response = otpController.resetPassword("test@example.com", "newPassword");

        // Verify the response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User not found.", response.getBody());
    }

    // @Test
    // void testForgotPassword_UserFound() {
    // // Create a mock user
    // User user = new User();
    // user.setEmail("test@example.com");

    // // Mock the userRepository.findByEmail method
    // when(userRepository.findByEmail(anyString())).thenReturn(user);

    // // Call the forgotPassword method
    // ResponseEntity<String> response =
    // otpController.forgotPassword("test@example.com");

    // // Verify the response
    // assertEquals(HttpStatus.OK, response.getStatusCode());
    // assertEquals("OTP sent successfully", response.getBody());
    // }

    @Test
    void testForgotPassword_UserNotFound() {
        // Mock the userRepository.findByEmail method to return null
        when(userRepository.findByEmail(anyString())).thenReturn(null);

        // Call the forgotPassword method
        ResponseEntity<String> response = otpController.forgotPassword("test@example.com");

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }

    @Test
    void testVerifyOTP_ValidOTP() {
        // Create a mock user
        User user = new User();
        user.setEmail("test@example.com");
        user.setOtp("12345678");
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(3));

        // Mock the userRepository.findByEmail method
        when(userRepository.findByEmail(anyString())).thenReturn(user);

        // Call the verifyOTP method
        ResponseEntity<String> response = otpController.verifyOTP("test@example.com", "12345678");

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OTP verified. Redirecting to password reset page.", response.getBody());
    }

    @Test
    void testVerifyOTP_InvalidOTP() {
        // Create a mock user
        User user = new User();
        user.setEmail("test@example.com");
        user.setOtp("12345678");
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));

        // Mock the userRepository.findByEmail method
        when(userRepository.findByEmail(anyString())).thenReturn(user);

        // Call the verifyOTP method with an invalid OTP
        ResponseEntity<String> response = otpController.verifyOTP("test@example.com", "654321");

        // Verify the response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid or expired OTP.", response.getBody());
    }

    @Test
    void testVerifyOTP_ExpiredOTP() {
        // Create a mock user
        User user = new User();
        user.setEmail("test@example.com");
        user.setOtp("12345678");
        user.setOtpExpiry(LocalDateTime.now().minusMinutes(5));

        // Mock the userRepository.findByEmail method
        when(userRepository.findByEmail(anyString())).thenReturn(user);

        // Call the verifyOTP method with an expired OTP
        ResponseEntity<String> response = otpController.verifyOTP("test@example.com", "12345678");

        // Verify the response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid or expired OTP.", response.getBody());
    }

    @Test
    void testVerifyOTP_UserNotFound() {
        // Mock the userRepository.findByEmail method to return null
        when(userRepository.findByEmail(anyString())).thenReturn(null);

        // Call the verifyOTP method
        ResponseEntity<String> response = otpController.verifyOTP("test@example.com", "12345678");

        // Verify the response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid or expired OTP.", response.getBody());
    }
}
