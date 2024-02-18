package com.example.Controller;

import com.example.Service.Toreviewservice;
import com.example.model.Paper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class ToReviewController {

    @Autowired
    private Toreviewservice toreviewservice;

    @GetMapping("/papers")
    public List<Paper> getAllPapersWithSubmissions() {
        return toreviewservice.getAllPapersWithSubmissions();
    }

    @GetMapping("/submissions/{userId}")
    public List<Map<String, Object>> getAllSubmissionInfo(@RequestParam("userId") Long userId) {
        return toreviewservice.getAllSubmissionInfo(userId);
    }

    @PatchMapping("/papers/{paperId}/submissions/{submissionId}/comment")
    public void saveComment(@PathVariable int paperId, @PathVariable int submissionId, @RequestParam String comment,
            @RequestParam int rating) {
        toreviewservice.saveComment(paperId, submissionId, comment, rating);
    }

}
