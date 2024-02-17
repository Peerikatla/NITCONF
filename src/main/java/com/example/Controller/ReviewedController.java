package com.example.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service.Reviewedservice;
import com.example.model.Paper;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ReviewedController {

    @Autowired
    private Reviewedservice reviewedservice;

    @GetMapping("/api/reviewed-papers")
    public List<Map<String, Object>> getPapersWithReviews(@RequestParam("userId") Long userId) {
        return reviewedservice.getPapersWithReviews(userId);
    }

    @PatchMapping("/api/update-review")
    public void updateReview(@RequestParam("paperId") int paperId, @RequestParam("submissionId") int submissionId, @RequestBody Map<String, Object> reviewData) {
        String comment = (String) reviewData.get("comment");
        int rating = (int) reviewData.get("rating");
        reviewedservice.updatecomment(paperId, submissionId, comment, rating);
    }
    
    @GetMapping("/api/all-papers")
    public List<Paper> getAllPapersWithSubmissions() {
        return reviewedservice.getAllPapersWithSubmissions();
    }
}
