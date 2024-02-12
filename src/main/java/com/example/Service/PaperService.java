package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Repository.PaperRepository;
import com.example.model.Paper;

public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    private List<Paper> papers;

    public List<Paper> getAllPapersWithSubmissions() {
        return (List<Paper>) paperRepository.findAll();
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
