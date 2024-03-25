package com.example.Controller;

import com.example.Repository.SubmissionRepository;
import com.example.Service.Reviewedservice;
import com.example.model.Paper;
import com.example.model.Submission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/reviewedsubmissions")
    public ResponseEntity<List<Map<String, Object>>> getPapersWithReviews(@RequestParam("userId") Integer userId) {
        List<Map<String, Object>> papersWithReviews = reviewedservice.getPapersWithReviews(userId);
        return new ResponseEntity<>(papersWithReviews, HttpStatus.OK);
    }
    
    @GetMapping("/reviewed/paper/submission")
    public ResponseEntity<Map<String, Object>> getSubmissionDetails(@RequestParam("submissionId") Integer submissionId) {
        Map<String, Object> submissionDetails = reviewedservice.getSubmissionDetails(submissionId);
        return new ResponseEntity<>(submissionDetails, HttpStatus.OK);
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
    public ResponseEntity<Void> saveComment(@RequestBody Map<String, Object> requestBody) {
        try {
            // Extract data from the request body
            Integer paperId = Integer.parseInt((String) requestBody.get("paperId"));
            Integer submissionId = Integer.parseInt((String) requestBody.get("submissionId"));
            String comment = (String) requestBody.get("comment");

            // Extract ratings from the request body
            Integer originality = Integer.parseInt((String) requestBody.get("originality"));
            Integer relevance = Integer.parseInt((String) requestBody.get("relevance"));
            Integer quality = Integer.parseInt((String) requestBody.get("quality"));
            Integer technicalContentAndAccuracy = Integer.parseInt((String) requestBody.get("TCA"));
            Integer significanceOfWork = Integer.parseInt((String) requestBody.get("significanceOfWork"));
            Integer appropriateForSAC = Integer.parseInt((String) requestBody.get("appropriateForSAC"));

            // Check if any of the ratings are invalid (e.g., out of range)
            if (originality < 0 || originality > 5 ||
                relevance < 0 || relevance > 5 ||
                quality < 0 || quality > 5 ||
                technicalContentAndAccuracy < 0 || technicalContentAndAccuracy > 5 ||
                significanceOfWork < 0 || significanceOfWork > 5 ||
                appropriateForSAC < 0 || appropriateForSAC > 5) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // Check if the comment is empty
            if (comment == null || comment.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // Call the service method to save the comment
            reviewedservice.updatecomment(paperId, submissionId, comment, originality, relevance, quality,
                    technicalContentAndAccuracy, significanceOfWork, appropriateForSAC);
            return new ResponseEntity<>(HttpStatus.OK); // Return success status
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Return bad request status if any rating is not a valid number
        } catch (Exception e) {
            e.printStackTrace(); // Log any exceptions for debugging
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return internal server error status
        }
    }

    @Autowired
    private SubmissionRepository submissionRepository;

    @GetMapping("/abstract")
    public ResponseEntity<Resource> viewAbstract(@RequestParam("submissionId") Integer submissionId) {
        try {
            // Fetch the submission information from the repository
            Submission submission = submissionRepository.findBysubmissionId(submissionId);
            if (submission == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Submission not found
            }

            // Get the file path for the abstract PDF from the submission model
            String filePath = submission.getLink();
            if (filePath == null || filePath.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Abstract file path not found
            }

            // Create a Resource object from the file path
            Resource resource = new FileSystemResource(filePath);

            // Check if the resource exists
            if (!resource.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Abstract file not found
            }

            // Build headers for the response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE); // Set content type to PDF

            // Return ResponseEntity with the file resource and headers
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Error reading file
        }
    }
    
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadPaper1(@RequestParam("submissionId") Integer submissionId) {
        try {
            // Fetch the submission information from the repository
            Submission submission = submissionRepository.findBysubmissionId(submissionId);
            if (submission == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Submission not found
            }

            // Get the file path for the PDF from the submission model
            String filePath = submission.getLink();
            if (filePath == null || filePath.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // PDF file path not found
            }

            // Create a Resource object from the file path
            Resource resource = new FileSystemResource(filePath);

            // Check if the resource exists
            if (!resource.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // PDF file not found
            }

            // Build headers for the response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

            // Return ResponseEntity with the file resource and headers
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}