package com.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/to-review")
    public String toReviewPage(Model model) {
        // Add logic to populate the model with data if needed
        return "To-review";
    }

    @GetMapping("/reviewed")
    public String reviewedPage(Model model) {
        // Add logic to populate the model with data if needed
        return "Reviewed";
    }
    
    @GetMapping("/notifications")
    public String notificationsPage(Model model) {
    	
    	return "Notifications";
    }
    
    @GetMapping("/profile")
    public String profilepage(Model model) {
    	return "Profile";
    }
    
    @GetMapping("/history")
    public String historypage(Model model) {
    	return "History";
    }
    
    // Add more controller methods for other pages/features...

}