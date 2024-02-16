package com.example.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.PaperRepository;
import com.example.model.Paper;
import com.example.model.Submission;

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

    public List<Map<String, Object>> getAllSubmissionInfo(Long userId) {
        List<Map<String, Object>> submissionInfos = new ArrayList<>();
        List<Paper> papers = (List<Paper>) paperRepository.findPapersByUserUserid(userId);

        if (papers == null) {
            return submissionInfos;
        }

        for (Paper paper : papers) {
            for (Submission submission : paper.getSubmissions()) {
                if (submission.getStatus() == "null") {
                    Map<String, Object> submissionInfo = new HashMap<>();
                    submissionInfo.put("title", paper.getTitle());
                    submissionInfo.put("submissionStatus", submission.getStatus());
                    submissionInfo.put("revisionStatus", paper.getRevisionStatus());
                    submissionInfo.put("deadline", submission.getDeadline());

                    // submissionInfo.put("abstractPdfLink", paper.getAbstractPdfLink());
                    // submissionInfo.put("downloadPdfLink", paper.getDownloadPdfLink());

                    submissionInfos.add(submissionInfo);
                }
            }
        }
        return submissionInfos;
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
    
}
