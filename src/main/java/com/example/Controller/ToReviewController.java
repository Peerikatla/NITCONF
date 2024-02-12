package com.example.Controller;

import com.example.Service.Toreviewservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class ToReviewController {

    @Autowired
    private Toreviewservice toreviewservice;

    @GetMapping("/api/to-review-submissions")
    public List<Map<String, Object>> getAllSubmissionInfo(@RequestParam("userId") Long userId) {
        return toreviewservice.getAllSubmissionInfo(userId);
    }

    @PatchMapping("/api/save-comment")
    public void saveComment(@RequestParam("paperId") int paperId, @RequestParam("submissionId") int submissionId, @RequestParam("comment") String comment, @RequestParam("rating") int rating) {
        toreviewservice.saveComment(paperId, submissionId, comment, rating);
    }
}
