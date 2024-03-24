package com.example.Service;

import java.util.ArrayList;
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
public class PreviousHistoryService {

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    SubmissionRepository submissionRepository;

    public List<Map<String, Object>> getAllHistory(Integer paperId) {

        List<Map<String, Object>> submissionInfos = new ArrayList<>();

        Paper paper = paperRepository.findByPaperId(paperId);
        if (paper == null)
            return null;

            int i = 1;
        for (Submission submission : paper.getSubmissions()) {
            Map<String, Object> Info = new HashMap<>();

            Info.put("deadline", submission.getDeadline());
            Info.put("submissionId", submission.getSubmissionId());
            Info.put("submissionNumber", i++);
            submissionInfos.add(Info);
        }

        return submissionInfos;
    }

    public Map<String, Object> getsubmissiondetails(Integer submissionId) {

        Submission submission = submissionRepository.findBysubmissionId(submissionId);
        if (submission == null)
            return null;

        Map<String, Object> Info = new HashMap<>();
        Info.put("comment", submission.getComment());
        Info.put("originality", submission.getOriginality());
        Info.put("relevance", submission.getRelevance());
        Info.put("quality", submission.getQuality());
        Info.put("TCA", submission.getTCA());
        Info.put("significanceOfWork", submission.getSignificanceOfWork());
        Info.put("appropriateForSAC", submission.getAppropriateForSAC());
        return Info;
    }

    public Map<String, Object> getpaperdetails(Integer paperId) {
        Paper paper = paperRepository.findByPaperId(paperId);
        if (paper == null)
            return null;

        System.out.println(paper.toString());
        Map<String, Object> Info = new HashMap<>();
        Info.put("title", paper.getTitle());
        Info.put("status", paper.getApprovestatus());
        return Info;
    }

}
