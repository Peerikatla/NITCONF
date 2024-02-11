package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import com.example.Repository.UserRepository;
import com.example.model.User;

/**
 * The UserService interface defines methods for user-related operations.
 */
public interface UserService {
	
	public static final UserRepository userrepo = null;

    /**
     * Registers a new user.
     *
     * @param user The User object containing registration information.
     */
    public static void registerUser(@NonNull User user) {
        userrepo.save(user);
    }

	public static void updateProfile(User user) {
		// TODO Auto-generated method stub
		
	}
  
}
