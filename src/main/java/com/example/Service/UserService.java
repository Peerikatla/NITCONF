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

    public User getUserById(Integer userId) {
        return userRepository.findByuserid(userId).orElse(null);
    }

    @SuppressWarnings("null")
    public User updateUserProfile(User updatedUser) {
        return userRepository.save(updatedUser);
    }

    public void updateUserProfileFields(Integer userId, String fullName, String username, String number, String specialization, Date dateOfBirth) {
        User user = userRepository.findByuserid(userId).orElse(null);
        if (user != null) {
            user.setFullName(fullName);
            user.setUsername(username);
            user.setNumber(number);
            user.setSpecialization(specialization);
            user.setDateOfBirth(dateOfBirth);
            userRepository.save(user);
        }
    }
  
}
