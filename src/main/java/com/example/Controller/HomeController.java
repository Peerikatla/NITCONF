package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Service.Toreviewservice;

/**
 * This class represents the controller for the home page and related functionalities.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private Toreviewservice toreviewservice;

    /**
     * Displays the home page.
     *
     * @return The name of the home page view.
     */
    @GetMapping("/Home")
    public String showHomePage() {
        return "Home";
    }

    /**
     * Retrieves submission information for the user to review.
     *
     * @param model The model object to add attributes to.
     * @return The name of the to-review page view.
     */
    @GetMapping("/To-review")
    public String getMethodName(Model model) {
        // Long userId = (Long) model.getAttribute("userId");
        Long userId = 1L;

        model.addAttribute("submissionInfos", toreviewservice.getAllSubmissionInfo(userId));
        return "To-review";
    }

    /**
     * Displays the reviewed page.
     *
     * @return The name of the reviewed page view.
     */
    @GetMapping("/Reviewed")
    public String ReviewedPage() {
        return "Reviewed";
    }

    /**
     * Displays the history page.
     *
     * @return The name of the history page view.
     */
    @GetMapping("/History")
    public String HistoryPage() {
        return "History";
    }

    /**
     * Displays the notifications page.
     *
     * @return The name of the notifications page view.
     */
    @GetMapping("/Notifications")
    public String NotificationsPage() {
        return "Notifications";
    }

    /**
     * Displays the profile page.
     *
     * @return The name of the profile page view.
     */
    @GetMapping("/Profile")
    public String ProfilePage() {
        return "Profile";
    }
}