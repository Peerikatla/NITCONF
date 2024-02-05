//package com.example.Controller;
//
//import com.example.model.Paper;
//import com.example.Service.PaperService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
///**
// * The ToReviewController class handles requests related to reviewing papers.
// */
//@Controller
//@RequestMapping("/to-review")
//public class ToReviewController {
//
//    private final PaperService paperService;
//
//    /**
//     * Constructs a ToReviewController with the specified PaperService.
//     *
//     * @param paperService The PaperService used for handling paper-related operations.
//     */
//    public ToReviewController(PaperService paperService) {
//        this.paperService = paperService;
//    }
//
//    /**
//     * Handles the request to view the "To Review" page, displaying papers with submissions.
//     *
//     * @param model The Spring Model to add attributes.
//     * @return The view name for the "To Review" page (in this case, "to-review").
//     */
//    @GetMapping("/view")
//    public String viewToReviewPage(Model model) {
//        // Retrieve the list of Papers with their Submissions from the database
//        List<Paper> papers = paperService.getAllPapersWithSubmissions();
//
//        model.addAttribute("papers", papers);
//
//        return "to-review";
//    }
//
//    /**
//     * Handles the request to save a review comment and rating for a paper submission.
//     *
//     * @param paperId      The ID of the Paper being reviewed.
//     * @param submissionId The ID of the Submission being reviewed.
//     * @param comment      The review comment.
//     * @param rating       The review rating.
//     * @return Redirects back to the "To Review" page after saving the comment.
//     */
//    @PostMapping("/save-comment")
//    public String saveComment(Long paperId, Long submissionId, String comment, int rating) {
//        // Implement logic to save the comment and rating for the specified Paper and Submission
//        // Use the paperService to update the database
//        paperService.saveComment(paperId, submissionId, comment, rating);
//
//        // Redirect back to the "To Review" page after saving the comment
//        return "redirect:/to-review/view";
//    }
//}

