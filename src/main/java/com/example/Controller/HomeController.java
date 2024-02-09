package com.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/Home")
    public String showHomePage() {
        return "Home"; 
    }

    @GetMapping("/To-review")
    public String redirectToReviewPage() {
        return "To-review"; 
    }    
    
    @GetMapping("/Reviewed")
    public String ReviewedPage() {
        return "Reviewed"; 
    }  
    
    @GetMapping("/History")
    public String HistoryPage() {
        return "History"; 
    }  
    @GetMapping("/Notifications")
    public String NotificationsPage() {
        return "Notifications"; 
    }   
    
    @GetMapping("/Profile")
    public String ProfilePage() {
        return "Profile"; 
    }   
    
}


