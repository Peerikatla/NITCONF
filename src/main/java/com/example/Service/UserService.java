package com.example.Service;

import com.example.model.User;

/**
 * The UserService interface defines methods for user-related operations.
 */
public interface UserService {

    /**
     * Registers a new user.
     *
     * @param user The User object containing registration information.
     */
    public void registerUser(User user);
  
}
