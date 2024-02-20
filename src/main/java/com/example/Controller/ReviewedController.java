package com.example.Controller;

import com.example.Service.Reviewedservice;
import com.example.model.Paper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReviewedController {

    @Autowired
    private Reviewedservice reviewedservice;

    @GetMapping("/papers")
    public List<Paper> getAllPapersWithSubmissions() {
        return reviewedservice.getAllPapersWithSubmissions();
    }

    @GetMapping("/papers/{userId}/reviews")
    public List<Map<String, Object>> getPapersWithReviews(@PathVariable Integer userId) {
        return reviewedservice.getPapersWithReviews(userId);
    }

    @PatchMapping("/papers/{paperId}/submissions/{submissionId}/comment")
    public void updateComment(@PathVariable int paperId,
                              @PathVariable int submissionId,
                              @RequestParam String comment,
                              @RequestParam int rating) {
        reviewedservice.updatecomment(paperId, submissionId, comment, rating);
    }
}
