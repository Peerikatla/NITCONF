package com.example.Service;


import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.PaperRepository;
import com.example.Repository.SubmissionRepository;
import com.example.model.Paper;
import com.example.model.Submission;

/**
 * This class represents a service for retrieving history-related information.
 */
@Service
public class HistoryService {
    @Autowired
    private PaperRepository paperRepository;
    
    @Autowired
    private SubmissionRepository submissionRepository;

    // private List<Paper> papers;

    /**
     * Retrieves all papers.
     * 
     * @return a list of papers
     */
    public List<Paper> getallPapers() {
        return (List<Paper>) paperRepository.findAll();
    }

    /**
     * Retrieves the history of papers for a specific user.
     * 
     * @param userId the ID of the user
     * @return a list of maps containing paper information
     */
    public List<Map<String, Object>> getAllHistory(Integer userId) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Paper> papers = paperRepository.findPapersByUserId(userId);
        LocalDate currentTime = LocalDate.now();

        for (Paper paper : papers) {
            boolean needsReview = paper.getApprovestatus() != null && getDeadline(paper).isBefore(currentTime);
            Integer submissionId = 0;
            for (Submission submission : paper.getSubmissions()) {
            	LocalDate deadline = submission.getDeadline();
            	 submissionId= submission.getSubmissionId();
            }
            
            if (needsReview) {
            	 Submission submission = submissionRepository.findBysubmissionId(submissionId);
                Map<String, Object> paperMap = new HashMap<>();
                
                paperMap.put("title", paper.getTitle());
                paperMap.put("status", paper.getApprovestatus());
                paperMap.put("revisionStatus", paper.getRevisionStatus());
                paperMap.put("deadline", submission.getDeadline());
                paperMap.put("paperId", paper.getPaperId());
                paperMap.put("submissionId", submission.getSubmissionId());
                result.add(paperMap);
            }
        }
        return result;
    }

    private LocalDate getDeadline(Paper paper) {
        List<Submission> submissions = paper.getSubmissions();
        return submissions.stream()
                .map(submission -> submission.getDeadline())
                .max(Comparator.naturalOrder())
                .orElse(null);
    }

    
   /* private LocalDate ConvertToLocalDateViaLocalDateTime(LocalDateTime dateToConvert) {
        return dateToConvert.toLocalDate();
    }*/
}
