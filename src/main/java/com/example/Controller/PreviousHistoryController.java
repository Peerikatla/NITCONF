package com.example.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.Service.PreviousHistoryService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * This class represents the controller for managing pre    vious history papers.
 * It handles HTTP requests related to retrieving history papers and their details.
 */
@RestController
@RequestMapping("/api")
public class PreviousHistoryController {

    @Autowired
    PreviousHistoryService historyService;

    /**
     * Retrieves all history papers for a given paper ID.
     *
     * @param paperId the ID of the paper
     * @return a ResponseEntity containing a list of history papers as Map objects
     */
    @GetMapping("/outdatedpapers/paper/allsubmissions")
    public ResponseEntity<List<Map<String, Object>>> getAllHistoryPapers(@RequestParam("paperId") Integer paperId) {
        List<Map<String, Object>> historyPapers = historyService.getAllHistory(paperId);
        return new ResponseEntity<>(historyPapers, HttpStatus.OK);
    }

    @GetMapping("/outdatedpapers/paper/submission")
    public ResponseEntity<Map<String, Object>> getMethodName(@RequestParam("submissionId") Integer submissionId) {
        Map<String, Object> submissionDetails = historyService.getsubmissiondetails(submissionId);
        return new ResponseEntity<>(submissionDetails, HttpStatus.OK);
    }
    

    /**
     * Retrieves the details of a specific history paper.
     *
     * @param paperId the ID of the paper
     * @return a ResponseEntity containing the details of the history paper as a Map object
     */
    @GetMapping("/outdatedpapers/paper/details")
    public ResponseEntity<Map<String, Object>> getSubmissionDetails(@RequestParam("paperId") Integer paperId) {
        Map<String, Object> historyPapers = historyService.getpaperdetails(paperId);
        return new ResponseEntity<>(historyPapers, HttpStatus.OK);
    }

}
