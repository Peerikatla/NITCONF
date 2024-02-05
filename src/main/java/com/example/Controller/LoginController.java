package com.example.Controller;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.User;
import com.example.Repository.UserRepository;

@Controller
@RequestMapping("/")
public class LoginController 
{
	
	@Autowired
	public UserRepository repo;
	@GetMapping("/loginPage")
	public String login(Model model) 
	{
		User user=new User(null, null, null, null, null, null, null, null);
		model.addAttribute("user",user);
		return "loginPage";
	}
	@PostMapping("/login111")
	public String loginUser(@ModelAttribute("user") User user)
	{
	 System.out.println(user.getUsername());
	 System.out.println(user.getPassword());

	 User userdata=repo.findByUsername(user.getUsername());
	 System.out.println(userdata.getPassword());
	 System.out.println(userdata.getPassword());
	 boolean k=user.getPassword().contentEquals(userdata.getPassword());
	 if(k==true )
	 {
		return "Home";
	}
	 
	 return "error";
	}
	
}
