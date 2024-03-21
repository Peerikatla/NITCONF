package com.example.Controller;

import com.example.Service.Toreviewservice;
import com.example.model.Paper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

/**
 * This class represents the controller for handling requests related to paper
 * reviews and submissions.
 */
@RestController
@RequestMapping("/api")
public class ToReviewController {

    @Autowired
    private Toreviewservice toreviewservice;

    /**
     * Retrieves a list of all papers with their corresponding submissions.
     *
     * @return A ResponseEntity containing a list of Paper objects with submissions.
     */
    @GetMapping("/papers")
    @ResponseBody
    public ResponseEntity<List<Paper>> getAllPapersWithSubmissions() {
        
        List<Paper> papers = toreviewservice.getAllPapersWithSubmissions();
        return new ResponseEntity<>(papers, HttpStatus.OK);
    }

    /**
     * Retrieves a list of submission information for a given user.
     *
     * @param userId The ID of the user.
     * @return A ResponseEntity containing a list of submission information as Map
     *         objects.
     */
    @GetMapping("/unreviewedsubmissions")
    @ResponseBody
    public ResponseEntity<List<Map<String, Object>>> getAllSubmissionInfo(@RequestParam("userId") Integer userId) {

        List<Map<String, Object>> submissionInfo = toreviewservice.getAllSubmissionInfo(userId);
        return new ResponseEntity<>(submissionInfo, HttpStatus.OK);
    }

    /**
     * Saves a comment and rating for a specific paper submission.
     *
     * @param paperId      The ID of the paper.
     * @param submissionId The ID of the submission.
     * @param comment      The comment to be saved.
     * @param rating       The rating to be saved.
     * @return A ResponseEntity with no content and HTTP status 204 (No Content).
     */
    @PatchMapping("/to-review/papers/submissions/comment")
    public ResponseEntity<Void> saveComment(@PathVariable Integer paperId, @PathVariable Integer submissionId,
            @RequestParam String comment, @RequestParam int Originality, @RequestParam int Relevance, @RequestParam int Quality,
            @RequestParam Integer TechnicalContentandAccuracy, @RequestParam Integer SignificanceOfWork, 
            @RequestParam Integer AppropriateForSAC) {

        toreviewservice.saveComment(paperId, submissionId, comment, Originality, Relevance, Quality, TechnicalContentandAccuracy, SignificanceOfWork, AppropriateForSAC);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
