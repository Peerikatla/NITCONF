package com.example.Controller;

import com.example.Service.Reviewedservice;
import com.example.model.Paper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * This class represents the controller for handling requests related to reviewed papers.
 */
@RestController
@RequestMapping("/api")
public class ReviewedController {

    @Autowired
    private Reviewedservice reviewedservice;

    /**
     * Retrieves all papers with their submissions.
     *
     * @return a list of papers with submissions
     */
    @GetMapping("/papers")
    public List<Paper> getAllPapersWithSubmissions() {
        return reviewedservice.getAllPapersWithSubmissions();
    }

    /**
     * Retrieves papers with their reviews for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of papers with reviews
     */
    @GetMapping("/papers/{userId}/reviews")
    public List<Map<String, Object>> getPapersWithReviews(@PathVariable Integer userId) {
        return reviewedservice.getPapersWithReviews(userId);
    }

    /**
     * Updates the comment and rating for a specific paper submission.
     *
     * @param paperId      the ID of the paper
     * @param submissionId the ID of the submission
     * @param comment      the new comment
     * @param rating       the new rating
     */
    @PatchMapping("/papers/{paperId}/submissions/{submissionId}/comment")
    public void updateComment(@PathVariable int paperId,
                              @PathVariable int submissionId,
                              @RequestParam String comment,
                              @RequestParam int rating) {
        reviewedservice.updatecomment(paperId, submissionId, comment, rating);
    }
}
