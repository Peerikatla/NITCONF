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

import jakarta.servlet.http.HttpSession;

import com.example.Repository.UserRepository;

/**
 * Controller class for handling user login functionality.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    public UserRepository repo;

    /**
     * Displays the login page.
     *
     * @param model The Spring Model to add attributes.
     * @return The view name for the login page.
     */
    @GetMapping("/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        // Inside your controller method
        model.addAttribute("isEditable", true);
        return "loginPage";
    }

    /**
     * Handles the user login process.
     *
     * @param user The User object containing login credentials.
     * @return The view name based on the login success or failure.
     */
    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, HttpSession session) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());

        // Retrieve user data from the repository based on the provided username.
        User userdata = repo.findByUsername(user.getUsername());

        System.out.println(userdata.getPassword());

        // Check if the provided password matches the stored password in the repository.
        boolean passwordMatch = user.getPassword().contentEquals(userdata.getPassword());

        // If the passwords match, redirect to the home page; otherwise, show an error page.
        if (passwordMatch) {
            session.setAttribute("userId", userdata.getUserid());
            System.out.println("done");
            return "redirect:/Home";
        }

        return "error";
    }
}
