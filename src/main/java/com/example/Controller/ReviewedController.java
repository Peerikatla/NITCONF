package com.example.Controller;

import com.example.Service.Reviewedservice;
import com.example.model.Paper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * This class represents the controller for handling requests related to
 * reviewed papers.
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
    @GetMapping("/submittedpapers")
    public ResponseEntity<List<Paper>> getAllPapersWithSubmissions() {
        List<Paper> papers = reviewedservice.getAllPapersWithSubmissions();
        return new ResponseEntity<>(papers, HttpStatus.OK);
    }

    /**
     * Retrieves papers with their reviews for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of papers with reviews
     */
    @GetMapping("/papers/reviews")
    public ResponseEntity<List<Map<String, Object>>> getPapersWithReviews(@PathVariable Integer userId) {
        List<Map<String, Object>> papersWithReviews = reviewedservice.getPapersWithReviews(userId);
        return new ResponseEntity<>(papersWithReviews, HttpStatus.OK);
    }

    /**
     * Updates the comment and rating for a specific paper submission.
     *
     * @param paperId      the ID of the paper
     * @param submissionId the ID of the submission
     * @param comment      the new comment
     * @param rating       the new rating
     * @return a success message
     */
    @PatchMapping("/reviewed/papers/submissions/comment")
    public ResponseEntity<String> updateCommentAndRating(@PathVariable Integer paperId,
            @PathVariable Integer submissionId,
            @RequestParam String comment,
            @RequestParam int rating) {
        reviewedservice.updatecomment(paperId, submissionId, comment, rating);
        return new ResponseEntity<>("Comment and rating updated successfully", HttpStatus.OK);
    }
}
