package com.example.Service;

import com.example.model.User;
import com.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmailId(String emailId) {
        return userRepository.findByEmailId(emailId);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public static User saveUser(User user) {
        // You can perform any additional validation or business logic here before saving
        return userRepository.save(user);
    }

//    public void deleteUser(Long userId) {
//        userRepository.deleteById(userId);
//    }
}
