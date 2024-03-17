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

@Service
public class HistoryService {
    @Autowired
    private PaperRepository paperRepository;

    private List<Paper> papers;

    public List<Paper> getallPapers() {
        return (List<Paper>) paperRepository.findAll();
    }

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
