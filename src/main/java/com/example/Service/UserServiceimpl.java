package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.Repository.UserRepository;

/**
 * The UserServiceimpl class provides an implementation for the UserService interface.
 * It handles user-related operations, such as user registration.
 */
@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserRepository repo;

    /**
     * Registers a new user by saving the user information to the repository.
     *
     * @param user The User object containing registration information.
     */
    @SuppressWarnings("null")
    @Override
    public void registerUser(User user) {
        repo.save(user);
    }
}
