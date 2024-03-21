package com.example.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.PaperRepository;
import com.example.Repository.SubmissionRepository;
import com.example.model.Paper;
import com.example.model.Submission;

@Service
public class Reviewedservice {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    private List<Paper> papers;

    public List<Paper> getAllPapersWithSubmissions() {
        return (List<Paper>) paperRepository.findAll();
    }

    public List<Map<String, Object>> getPapersWithReviews(Integer userId) {
        List<Map<String, Object>> result = new ArrayList<>();

        List<Paper> papers = paperRepository.findPapersByUserId(userId);

        for (Paper paper : papers) {

            if (paper.getApprovestatus() == null) {

                boolean hasReview = false;
                Integer submissionId = 0;

                for (Submission submission : paper.getSubmissions()) {

                    System.out.println(submission.getStatus());
                    if (submission.getStatus().equals("reviewed") 
                    && submission.getDeadline().compareTo(new Date()) > 0
                    ) {
                        System.out.println("reviewed found");   
                        hasReview = true;
                        submissionId = submission.getSubmissionId();
                        break;
                    }
                }

                Submission submission = submissionRepository.findBysubmissionId(submissionId);

                if (hasReview) {
                    Map<String, Object> paperMap = new HashMap<>();
                    paperMap.put("title", paper.getTitle());
                    paperMap.put("status", "Reviewed");
                    paperMap.put("revisionStatus", paper.getRevisionStatus());
                    paperMap.put("deadline", convertToLocalDateViaSqlDate(submission.getDeadline()));
                    paperMap.put("comment", submission.getComment());
                    paperMap.put("originality", submission.getOriginality());
                    paperMap.put("relevance", submission.getRelevance());
                    paperMap.put("quality", submission.getQuality());
                    paperMap.put("technicalContentandAccuracy", submission.getTechnicalContentandAccuracy());
                    paperMap.put("significanceOfWork", submission.getSignificanceOfWork());
                    paperMap.put("appropriateForSAC", submission.getAppropriateForSAC());
                    paperMap.put("link", submission.getLink());
                    result.add(paperMap);
                }
            }

        }

        return result;
    }

    private LocalDate convertToLocalDateViaSqlDate(Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    private Paper findPaperById(int paperId) {
        return papers.stream()
                .filter(paper -> paper.getPaperId() == paperId)
                .findFirst()
                .orElse(null);
    }

    public void updatecomment(Integer paperId, Integer submissionId, String comment, Integer Originality,
            Integer Relevance, Integer Quality, Integer TechnicalContentandAccuracy, Integer SignificanceOfWork, Integer AppropriateForSAC) {
        findPaperById(paperId).getSubmissions().stream()
                .filter(submission -> submission.getSubmissionId() == submissionId)
                .findFirst()
                .ifPresent(submission -> {
                    submission.setComment(comment);
                    submission.setOriginality(Originality);
                    submission.setRelevance(Relevance);
                    submission.setQuality(Quality);
                    submission.setTechnicalContentandAccuracy(TechnicalContentandAccuracy);
                    submission.setSignificanceOfWork(SignificanceOfWork);
                    submission.setAppropriateForSAC(AppropriateForSAC);
                });
    }
}
