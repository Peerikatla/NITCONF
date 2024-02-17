package com.example.Service;


import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.UserRepository;
import com.example.model.User;

/**
 * The UserService interface defines methods for user-related operations.
 */
@Service
public class UserService {
	
    @Autowired
	private UserRepository userRepository;

    @SuppressWarnings("null")
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @SuppressWarnings("null")
    public User updateUserProfile(User updatedUser) {
        return userRepository.save(updatedUser);
    }

    public void updateUserProfileFields(Long userId, String firstName, String lastName, String username, String number, String specialization, Date dateOfBirth) {
        @SuppressWarnings("null")
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setNumber(number);
            user.setSpecialization(specialization);
            user.setDateOfBirth(dateOfBirth);
            userRepository.save(user);
        }
    }
  
}
