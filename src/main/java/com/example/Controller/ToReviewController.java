package com.example.Controller;

import com.example.Service.Toreviewservice;
import com.example.model.Paper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

/**
 * This class represents the controller for handling requests related to paper reviews and submissions.
 */
@RestController
@RequestMapping("/api")
public class ToReviewController {

    @Autowired
    private Toreviewservice toreviewservice;

    /**
     * Retrieves a list of all papers with their corresponding submissions.
     *
     * @return A list of Paper objects with submissions.
     */
    @GetMapping("/papers")
    public List<Paper> getAllPapersWithSubmissions() {
        return toreviewservice.getAllPapersWithSubmissions();
    }

    /**
     * Retrieves a list of submission information for a given user.
     *
     * @param userId The ID of the user.
     * @return A list of submission information as Map objects.
     */
    @GetMapping("/submissions/{userId}")
    public List<Map<String, Object>> getAllSubmissionInfo(@RequestParam("userId") Integer userId) {
        return toreviewservice.getAllSubmissionInfo(userId);
    }

    /**
     * Saves a comment and rating for a specific paper submission.
     *
     * @param paperId      The ID of the paper.
     * @param submissionId The ID of the submission.
     * @param comment      The comment to be saved.
     * @param rating       The rating to be saved.
     */
    @PatchMapping("/papers/{paperId}/submissions/{submissionId}/comment")
    public void saveComment(@PathVariable Integer paperId, @PathVariable Integer submissionId, @RequestParam String comment,
                            @RequestParam int rating) {
        toreviewservice.saveComment(paperId, submissionId, comment, rating);
    }

}
