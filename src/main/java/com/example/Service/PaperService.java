package com.example.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Paper;
import com.example.model.Submission;

public class PaperService {

    private List<Paper> papers;

    public List<Paper> getAllPapersWithSubmissions() {
        return papers;
    }

    public void saveComment(int paperId, int submissionId, String comment, int rating) {
        findPaperById(paperId).getSubmissions().stream()
                .filter(submission -> submission.getSubmissionId() == submissionId)
                .findFirst()
                .ifPresent(submission -> {
                    submission.setComment(comment);
                    submission.setRating(rating);
                });
    }

    private Paper findPaperById(int paperId) {
        return papers.stream()
                .filter(paper -> paper.getPaperId() == paperId)
                .findFirst()
                .orElse(null);
    }

    // public Paper getReviewedPaper(int paperId) {
    //    return papers.stream()
    //            .filter(paper -> paper.getStatus() == 2)
    //            .findFirst()
    //            .orElse(null);
    // }
}
