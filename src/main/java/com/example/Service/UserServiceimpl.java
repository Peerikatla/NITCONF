package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.Repository.UserRepository;

@Service
public class UserServiceimpl implements UserService
{
	@Autowired
	private UserRepository repo;
	
	@Override
	public void registerUser(User user) {
	
		repo.save(user);
	}

	
	
	
	

}
