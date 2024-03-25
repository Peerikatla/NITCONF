package com.example.Controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Repository.UserRepository;
import com.example.Service.OTPService;
import com.example.model.User;

@RestController
@RequestMapping("/api")
public class OTPController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPService otpService;

    @PostMapping("/forgot-password/sendOTP")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            otpService.generateAndSendOtp(user);
            return new ResponseEntity<>("OTP sent successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/forgot-password/verifyOTP")
    public ResponseEntity<?> verifyOTP(@RequestParam("email") String email, @RequestParam("otp") String otp) {
        User user = userRepository.findByEmail(email);

        if (user != null && user.getOtp() != null && user.getOtp().equals(otp) &&
               LocalDateTime.now().isBefore(user.getOtpExpiry())) {
            user.setOtp(null); // Clear OTP
            user.setOtpExpiry(null);
            userRepository.save(user);
            return ResponseEntity.ok("OTP verified. Redirecting to password reset page.");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired OTP.");
        }
    }

    @PostMapping("reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("email") String email, @RequestParam("password") String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setPassword(password);
            userRepository.save(user);
            return ResponseEntity.ok("Password reset successfully.");
        } else {
            return ResponseEntity.badRequest().body("User not found.");
        }
    }
}
