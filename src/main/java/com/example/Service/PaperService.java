/*package com.example.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Paper;
import com.example.model.Submission;

public class PaperService {

    private List<Paper> papers; // Assume this list is populated with data from your data source

    public List<Paper> getAllPapersWithSubmissions() {
        // Implement logic to fetch papers with submissions from your data source (e.g., database)
        // For demonstration purposes, a mock implementation is provided here:

        List<Paper> papersWithSubmissions = new ArrayList<>();

        for (Paper paper : papers) {
            List<Submission> submissions = paper.getSubmissions();
            if (submissions != null && !submissions.isEmpty()) {
                papersWithSubmissions.add(paper);
            }
        }

        return papersWithSubmissions;
    }

    public void saveComment(Long paperId, Long submissionId, String comment, int rating) {
        // Implement logic to save a comment and rating for a specific paper and submission
        // For demonstration purposes, a mock implementation is provided here:

        Paper targetPaper = findPaperById(paperId);
        if (targetPaper != null) {
            Submission targetSubmission = findSubmissionById(targetPaper, submissionId);
            if (targetSubmission != null) {
                targetSubmission.setComment(comment);
                targetSubmission.setRating(rating);
            }
        }
    }

    // Helper method to find a Paper by its ID
    private Paper findPaperById(Long paperId) {
        for (Paper paper : papers) {
            if (paper.getPaperId().equals(paperId)) {
                return paper;
            }
        }
        return null;
    }

    // Helper method to find a Submission by its ID within a Paper
    private Submission findSubmissionById(Paper paper, Long submissionId) {
        List<Submission> submissions = paper.getSubmissions();
        if (submissions != null) {
            for (Submission submission : submissions) {
                if (submission.getSubmissionId().equals(submissionId)) {
                    return submission;
                }
            }
        }
        return null;
    }

	public Paper getReviewedPaper() {
		// TODO Auto-generated method stub
		return null;
	}
}*/
