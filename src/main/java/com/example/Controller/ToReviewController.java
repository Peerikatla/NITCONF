package com.example.Controller;

import com.example.Repository.SubmissionRepository;
import com.example.Service.Toreviewservice;

import com.example.model.Paper;
import com.example.model.Submission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
//import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import java.io.IOException;

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
        if(papers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
        if(submissionInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity<Void> saveComment(@RequestBody Map<String, Object> requestBody) {
        try {
        	String paperIdStr = (String) requestBody.get("paperId");
        	Integer paperId = Integer.parseInt(paperIdStr);
        	
        	String submissionIdStr = (String) requestBody.get("submissionId");
        	Integer submissionId = Integer.parseInt(submissionIdStr);
        	 String comment = (String) requestBody.get("comment");

        	String originalityStr = (String) requestBody.get("originality");
        	Integer originality = Integer.parseInt(originalityStr);

        	String relevanceStr = (String) requestBody.get("relevance");
        	Integer relevance = Integer.parseInt(relevanceStr);

        	String qualityStr = (String) requestBody.get("quality");
        	Integer quality = Integer.parseInt(qualityStr);

        	String technicalContentAndAccuracyStr = (String) requestBody.get("TAC");
        	Integer technicalContentAndAccuracy = Integer.parseInt(technicalContentAndAccuracyStr);

        	String significanceOfWorkStr = (String) requestBody.get("significanceOfWork");
        	Integer significanceOfWork = Integer.parseInt(significanceOfWorkStr);

        	String appropriateForSACStr = (String) requestBody.get("appropriateForSAC");
        	Integer appropriateForSAC = Integer.parseInt(appropriateForSACStr);


            // Check if any of the required parameters are null
            if (paperId == null || submissionId == null || comment == null || originality == null || relevance == null
                    || quality == null || technicalContentAndAccuracy == null || significanceOfWork == null
                    || appropriateForSAC == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Return bad request status if any parameter is missing
            }

            // Call the service method to save the comment
            toreviewservice.saveComment(paperId, submissionId, comment, originality, relevance, quality,
                    technicalContentAndAccuracy, significanceOfWork, appropriateForSAC);
            return new ResponseEntity<>(HttpStatus.OK); // Return success status
        } catch (Exception e) {
            e.printStackTrace(); // Log any exceptions for debugging
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return internal server error status
        }
    }
    
    
   
}
    
