package com.example.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpSession;

import com.example.Service.Reviewedservice;
import com.example.Service.Toreviewservice;
import com.example.Service.UserService;
import com.example.model.User;

/**
 * This class represents the controller for the home page and related functionalities.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private Toreviewservice toreviewservice;
    
    @Autowired
    private Reviewedservice reviewedservice;

    @Autowired
    private UserService userService;

    /**
     * Displays the home page.
     *
     * @return The name of the home page view.
     */
    @Operation(summary = "Display home page")
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
    @Operation(summary = "Retrieve submission information for review")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved submission information"),
        @ApiResponse(responseCode = "404", description = "Submission information not found")
    })
    @GetMapping("/To-review")
    public String getMethodName(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        System.out.println(userId);

        model.addAttribute("submissionInfos", toreviewservice.getAllSubmissionInfo(userId));
        return "To-review";
    }

    /**
     * Displays the reviewed page.
     *
     * @return The name of the reviewed page view.
     */
    @Operation(summary = "Display reviewed page")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved submission information"),
        @ApiResponse(responseCode = "404", description = "Submission information not found")
    })
    @GetMapping("/Reviewed")
    public String ReviewedPage(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        List<Map<String, Object>> reviewedPapers = reviewedservice.getPapersWithReviews(userId);
        model.addAttribute("reviewedPapers", reviewedPapers);
        return "Reviewed";
    }

    /**
     * Displays the history page.
     *
     * @return The name of the history page view.
     */
    @Operation(summary = "Display history page")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved submission information"),
        @ApiResponse(responseCode = "404", description = "Submission information not found")
    })
    @GetMapping("/History")
    public String HistoryPage( Model model, HttpSession session) {
        return "History";
    }

    /**
     * Displays the notifications page.
     *
     * @return The name of the notifications page view.
     */
    @Operation(summary = "Display notifications page")
    @GetMapping("/Notifications")
    public String NotificationsPage() {
        return "Notifications";
    }

    /**
     * Displays the profile page.
     *
     * @return The name of the profile page view.
     */
    @Operation(summary = "Display profile page")
    @GetMapping("/Profile")
    public String ProfilePage(Model model, HttpSession session) {
        System.out.println("Inside UserController - /profile");
        Integer userId = (Integer) session.getAttribute("userId");
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        System.out.println(session.getAttribute("userId"));
        System.out.println("added");
        System.out.println(user.getSpecialization());
        return "Profile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate the session
        session.invalidate();
        // Redirect to the login page
        return "redirect:/loginPage";
    }
}
