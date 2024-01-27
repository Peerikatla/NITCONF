package com.example.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Service.UserService;
import com.example.model.User;

public class UserController {
	@PostMapping("/updateProfile")
	public String updateProfile(@ModelAttribute User user) {
	    UserService.saveUser(user);
	    return "redirect:/profile";
	}
}
