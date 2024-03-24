package com.example.Service;

import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Date;
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
    
 // Initialize the papers list in the constructor
    public Reviewedservice() {
        papers = new ArrayList<>();
    }

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
                    LocalDate deadline = submission.getDeadline();
                    if ("reviewed".equals(submission.getStatus()) && deadline.compareTo(LocalDate.now()) > 0) {
                        System.out.println("reviewed found");
                        hasReview = true;
                        submissionId = submission.getSubmissionId();
                        break;
                    }
                }

                if (hasReview) {
                    Submission submission = submissionRepository.findBysubmissionId(submissionId);
                    if (submission != null) {
                        Map<String, Object> paperMap = new HashMap<>();
                        paperMap.put("submissionId", submission.getSubmissionId());
                        paperMap.put("paperId", paper.getPaperId());
                        paperMap.put("title", paper.getTitle());
                        paperMap.put("status", "Reviewed");
                        paperMap.put("revisionStatus", paper.getRevisionStatus());
                        paperMap.put("deadline", submission.getDeadline());
                        paperMap.put("comment", submission.getComment());
                        paperMap.put("originality", submission.getOriginality());
                        paperMap.put("relevance", submission.getRelevance());
                        paperMap.put("quality", submission.getQuality());
                        paperMap.put("technicalContentAndAccuracy", submission.getTechnicalContentAndAccuracy());
                        paperMap.put("significanceOfWork", submission.getSignificanceOfWork());
                        paperMap.put("appropriateForSAC", submission.getAppropriateForSAC());
                        paperMap.put("link", submission.getLink());
                        result.add(paperMap);
                    }
                }
            }
        }
        return result;
    }


   

    private Paper findPaperById(int paperId) {
        return paperRepository.findById(paperId).orElse(null);
    }

    
   public void updatecomment(Integer paperId, Integer submissionId, String comment, Integer Originality,
	        Integer Relevance, Integer Quality, Integer TechnicalContentAndAccuracy, Integer SignificanceOfWork, Integer AppropriateForSAC) {
	    Paper paper = findPaperById(paperId);
	    if (paper != null) {
	        paper.getSubmissions().stream()
	                .filter(submission -> submission.getSubmissionId() == submissionId)
	                .findFirst()
	                .ifPresent(submission -> {
	                    submission.setComment(comment);
	                    submission.setOriginality(Originality);
	                    submission.setRelevance(Relevance);
	                    submission.setQuality(Quality);
	                    submission.setTechnicalContentAndAccuracy(TechnicalContentAndAccuracy);
	                    submission.setSignificanceOfWork(SignificanceOfWork);
	                    submission.setAppropriateForSAC(AppropriateForSAC);
	                    submissionRepository.save(submission);
	                });
	    } else {
	        // Handle case where paper is not found (e.g., log error or throw exception)
	        // For example:
	        throw new IllegalArgumentException("Paper with ID " + paperId + " not found");
	    }
	}
 
   
    public Map<String, Object> getSubmissionDetails(Integer submissionId) {

        Submission submission = submissionRepository.findBysubmissionId(submissionId);
        if (submission != null) {
            Map<String, Object> submissionDetails = new HashMap<>();
            submissionDetails.put("submissionId", submission.getSubmissionId());
            submissionDetails.put("comment", submission.getComment());
            submissionDetails.put("originality", submission.getOriginality());
            submissionDetails.put("relevance", submission.getRelevance());
            submissionDetails.put("quality", submission.getQuality());
            submissionDetails.put("technicalContentAndAccuracy", submission.getTechnicalContentAndAccuracy());
            submissionDetails.put("significanceOfWork", submission.getSignificanceOfWork());
            submissionDetails.put("appropriateForSAC", submission.getAppropriateForSAC());
            return submissionDetails;
        }

        throw new UnsupportedOperationException("Unimplemented method 'getSubmissionDetails'");
    }
}
