package com.example.Controller;

import com.example.model.Paper;

//import com.example.model.Submission;
import com.example.Service.PaperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/to-review")
public class ToReviewController {

    private final PaperService paperService;

    public ToReviewController(PaperService paperService) {
        this.paperService = paperService;
    }

    @GetMapping("/view")
    public String viewToReviewPage(Model model) {
        // Retrieve the list of Papers with their Submissions from the database
        List<Paper> papers = paperService.getAllPapersWithSubmissions();

        model.addAttribute("papers", papers);

        return "to-review";
    }

    // You can add additional methods to handle review-related actions such as saving comments
    // Example for saving a comment
    @PostMapping("/save-comment")
    public String saveComment(Long paperId, Long submissionId, String comment, int rating) {
        // Implement logic to save the comment and rating for the specified Paper and Submission
        // Use the paperService to update the database
        paperService.saveComment(paperId, submissionId, comment, rating);

        // Redirect back to the "To Review" page after saving the comment
        return "redirect:/to-review/view";
    }
}
