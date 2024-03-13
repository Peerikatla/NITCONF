package com.example.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.model.User;
import com.example.Repository.UserRepository;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserRepository repo;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "loginPage";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, HttpSession session) {
        User userdata = repo.findByUsername(user.getUsername());
        if (userdata != null && userdata.getPassword().equals(user.getPassword())) {
            session.setAttribute("userId", userdata.getUserid());
            return "redirect:/Home";
        }
        return "error";
    }
}