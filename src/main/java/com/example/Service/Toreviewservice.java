package com.example.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.PaperRepository;
import com.example.model.Paper;
// import com.example.model.Submission;

@Service
public class Toreviewservice {

    @Autowired
    private PaperRepository paperRepository;

    private List<Paper> papers;

    public Toreviewservice(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    public List<Paper> getAllPapersWithSubmissions() {
        return (List<Paper>) paperRepository.findAll();
    }

    public List<Map<String, Object>> getAllSubmissionInfo(Integer userId) {
        List<Map<String, Object>> submissionInfos = new ArrayList<>();
        List<Paper> papers = (List<Paper>) paperRepository.findPapersByUserId(userId);

        if (papers == null) {
            return submissionInfos;
        }

        for (Paper paper : papers) {
            boolean torev = paper.getSubmissions().stream()
                    .anyMatch(submission -> submission.getStatus() == null);
            
            if (torev) {
                Map<String, Object> info = new HashMap<>();
                info.put("title", paper.getTitle());
                info.put("status", "To Review");
                info.put("revisionStatus", paper.getRevisionStatus());
                info.put("deadline", paper.getSubmissions().get(paper.getSubmissions().size() - 1).getDeadline());
                info.put("pdflink", paper.getSubmissions().get(paper.getSubmissions().size() - 1).getLink());
                submissionInfos.add(info);
            }
        }
        return submissionInfos;
    }

    public void saveComment(Integer paperId, Integer submissionId, String comment, int rating) {
        findPaperById(paperId).getSubmissions().stream()
                .filter(submission -> submission.getSubmissionId() == submissionId)
                .findFirst()
                .ifPresent(submission -> {
                    submission.setComment(comment);
                    submission.setRating(rating);
                });
    }

    private Paper findPaperById(Integer paperId) {
        return papers.stream()
                .filter(paper -> paper.getPaperId() == paperId)
                .findFirst()
                .orElse(null);
    }
    
}
