package com.example.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.PaperRepository;
import com.example.model.Paper;
import com.example.model.Submission;

@Service
public class Reviewedservice {

    @Autowired
    private PaperRepository paperRepository;

    private List<Paper> papers;

    public List<Paper> getAllPapersWithSubmissions() {
        return (List<Paper>) paperRepository.findAll();
    }

    public List<Map<String, Object>> getPapersWithReviews(Integer userId) {
        List<Map<String, Object>> result = new ArrayList<>();

        List<Paper> papers = paperRepository.findPapersByUserUserid(userId);

        for (Paper paper : papers) {
            boolean hasReview = paper.getSubmissions().stream()
                    .anyMatch(submission -> submission.getComment() != null && submission.getRating() != 0);

            if (hasReview) {
                Map<String, Object> paperMap = new HashMap<>();
                paperMap.put("title", paper.getTitle());
                paperMap.put("status", "Reviewed");
                paperMap.put("revisionStatus", paper.getRevisionStatus());
                paperMap.put("deadline", getLatestDeadline(paper));
                result.add(paperMap);
            }
        }

        return result;
    }

    private Date getLatestDeadline(Paper paper) {
        return paper.getSubmissions()
                .stream()
                .map(Submission::getDeadline)
                .max(Date::compareTo)
                .orElse(null);
    }

    private Paper findPaperById(int paperId) {
        return papers.stream()
                .filter(paper -> paper.getPaperId() == paperId)
                .findFirst()
                .orElse(null);
    }

    public void updatecomment(Integer paperId, Integer submissionId, String comment, int  rating) {
        findPaperById(paperId).getSubmissions().stream()
                .filter(submission -> submission.getSubmissionId() == submissionId)
                .findFirst()
                .ifPresent(submission -> {
                    submission.setComment(comment);
                    submission.setRating(rating);
                });
    }
}



