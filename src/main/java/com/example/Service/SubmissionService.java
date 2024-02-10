package com.example.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Repository.SubmissionRepository;
import com.example.model.Paper;
import com.example.model.Submission;

/**
 * This class represents a service for managing submissions.
 */
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    /**
     * Retrieves a submission by its ID.
     *
     * @param submissionId the ID of the submission
     * @return the submission with the given ID, or null if not found
     */
    public Submission getSubmissionById(int submissionId) {
        Optional<Submission> optionalSubmission = submissionRepository.findBysubmissionId(submissionId);
        return optionalSubmission.orElse(null);
    }

    /**
     * Retrieves all unreviewed submissions.
     *
     * @return a list of unreviewed submissions
     */
    public List<Submission> getAllUnreviewedSubmissions() {
        return submissionRepository.findAllUnreviewedSubmissions();
    }

    /**
     * Retrieves all reviewed submissions.
     *
     * @return a list of reviewed submissions
     */
    public List<Submission> getallReviewedSubmissions(){
        return submissionRepository.findAllReviewedSubmissions();
    }
    
    /**
     * Retrieves all submissions for a given paper.
     *
     * @param paperId the ID of the paper
     * @return a list of submissions for the paper
     */
    public List<Submission> getSubmissionsForPaper(int paperId) {
        return submissionRepository.findSubmissionsForPaper(paperId);
    }
    
    /**
     * Finds a submission by its ID within a paper.
     *
     * @param paper the paper containing the submission
     * @param submissionId the ID of the submission
     * @return the submission with the given ID, or null if not found
     */
    private Submission findSubmissionById(Paper paper, int submissionId){
        return paper.getSubmissions().stream()
                .filter(submission -> submission.getSubmissionId() == submissionId)
                .findFirst()
                .orElse(null);
    }
}
