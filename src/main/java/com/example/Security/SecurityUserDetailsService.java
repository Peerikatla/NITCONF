package com.example.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Repository.UserRepository;
import com.example.model.User;


@Service
public class SecurityUserDetailsService implements UserDetailsService{
	
	
	@Autowired
	private UserRepository userRepository; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		User user = userRepository.findByUsername(username);
		
		CustomUser CUser = new CustomUser(user);
		
		if(user!= null)
			return CUser;
		
		System.out.println("Usernotfound");
		throw new UsernameNotFoundException("Username Not Found");
	} 
	
	
}