package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Handler {

    @GetMapping("/error")
    public String handleError() {
        // Provide your own error handling logic
        return "error"; // Return the name of your error page (e.g., "error.html")
    }
    
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
