package com.example.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

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
        return userRepository.findByuserid(userId);
    }

    @SuppressWarnings("null")
    public User updateUserProfile(User updatedUser) {
        return userRepository.save(updatedUser);
    }

    public void updateUserProfileFields(User existingUser, String fullName, String username, String number, String specialization, Date DateofBirth) {
        if (existingUser != null) {
            existingUser.setFullName(fullName);
            existingUser.setUsername(username);
            existingUser.setNumber(number);
            existingUser.setSpecialization(specialization);
            existingUser.setDateOfBirth(DateofBirth);
            userRepository.save(existingUser);
        }
    }

    public Map<String, Object> getUserInfo(Integer userId) {

        Map<String, Object> userInfo = new HashMap<>();

        User user = userRepository.findByuserid(userId);

        if(user != null){
            userInfo.put( "userId", user.getUserid());
            userInfo.put( "fullName", user.getFullName());
            userInfo.put( "username", user.getUsername());
            userInfo.put( "number", user.getNumber());
            userInfo.put( "specialization", user.getSpecialization());
            userInfo.put( "dateOfBirth", user.getDateOfBirth());
            userInfo.put("email", user.getEmail());
        }
        
        return userInfo;
    }
  
}