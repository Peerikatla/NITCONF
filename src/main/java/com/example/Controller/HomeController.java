package com.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The HomeController class handles requests related to the home page.
 */
@Controller
public class HomeController {

    /**
     * Handles the request to the home page.
     *
     * @return The view name for the home page (in this case, "loginPage").
     */
    @GetMapping("/")
    public String home() {
        return "loginPage";
    }
}
