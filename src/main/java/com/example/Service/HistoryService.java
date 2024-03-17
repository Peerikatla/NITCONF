package com.example.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.PaperRepository;
import com.example.model.Paper;

/**
 * This class represents a service for retrieving history-related information.
 */
@Service
public class HistoryService {
    @Autowired
    private PaperRepository paperRepository;

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
    public List<Map<String, Object>> getAllHistory(Integer userId){
        List<Map<String, Object>> result = new ArrayList<>();
        List<Paper> papers = paperRepository.findPapersByUserId(userId);
        for (Paper paper : papers) {
            if (paper.getSubmissions().stream().anyMatch(submission -> submission.getComment() != null && submission.getRating() != 0)) {
                Map<String, Object> paperMap = new HashMap<>();
                paperMap.put("title", paper.getTitle());
                paperMap.put("status", "Reviewed");
                paperMap.put("revisionStatus", paper.getRevisionStatus());
                paperMap.put("deadline", paper.getSubmissions().stream().map(submission -> submission.getDeadline()).max(Comparator.naturalOrder()).orElse(null));
                result.add(paperMap);
            }
        }
        return result;
    }
}
