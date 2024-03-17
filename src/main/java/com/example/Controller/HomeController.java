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
// import com.example.Service.UserService;
// import com.example.model.User;

/**
 * This class represents the controller for the home page and related
 * functionalities.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private Toreviewservice toreviewservice;

    @Autowired
    private Reviewedservice reviewedservice;

    // @Autowired
    // private UserService userService;

    private Integer GetUserId(HttpSession session) {
        return (Integer) session.getAttribute("userid");
    }

    /**
     * Displays the home page.
     *
     * @return The name of the home page view.
     */
    @Operation(summary = "Display home page")
    @GetMapping("/Home")
    public String showHomePage(HttpSession session) {

        Integer userId = GetUserId(session);
        if (userId != null) {
            System.out.println("User ID in HomePage: " + userId); // Print retrieved userId for debugging
        }
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
    public String Toreviewpage(Model model, HttpSession session) {

        Integer userId = GetUserId(session);
        System.out.println("User ID in Toreviewpage: " + userId); // Print retrieved userId for debugging

        List<Map<String, Object>> submissionInfos = toreviewservice.getAllSubmissionInfo(userId);

        System.out.println("Retrieved Submission Information:");
        if (submissionInfos.size() != 0) {
            System.out.println("Number of submissions: " + submissionInfos.size());
            for (Map<String, Object> submissionInfo : submissionInfos) {
                System.out.println("  - Title: " + submissionInfo.get("title"));
                System.out.println("  - Submission Status: " + submissionInfo.get("submissionStatus"));
                System.out.println("  - Revision Status: " + submissionInfo.get("revisionStatus"));
                System.out.println("  - Deadline: " + submissionInfo.get("deadline"));
            }
        } else {
            System.out.println("No submissions found for user.");
        }

        model.addAttribute("submissionInfos", submissionInfos);
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

        Integer userId = GetUserId(session);
        System.out.println("User ID in ReviewedPage: " + userId); // Print retrieved userId for debugging

        List<Map<String, Object>> reviewedPapers = reviewedservice.getPapersWithReviews(userId);

        System.out.println("Retrieved Reviewed Papers:");
        if( reviewedPapers.size() != 0){    
            for (Map<String, Object> reviewedPaper : reviewedPapers) {
                System.out.println("  - Title: " + reviewedPaper.get("title"));
                System.out.println("  - Submission Status: " + reviewedPaper.get("submissionStatus"));
                System.out.println("  - Revision Status: " + reviewedPaper.get("revisionStatus"));
                System.out.println("  - Deadline: " + reviewedPaper.get("deadline"));
            }
        }
        else{
            System.out.println("No reviewed papers found for user.");
        }

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
    public String HistoryPage(Model model, HttpSession session) {
        Integer userId = GetUserId(session);
        System.out.println("User ID in HistoryPage: " + userId); // Print retrieved userId for debugging

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
    @GetMapping("/Profile")
    public String ProfilePage(Model model, HttpSession session) {
        Integer userId = GetUserId(session);
        System.out.println("User ID in ProfilePage: " + userId);

        // User user = userService.getUserById(userId);
        // System.out.println(user.toString());
        // model.addAttribute("user", user);

        model.addAttribute("userid", userId);
        return "Profile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}