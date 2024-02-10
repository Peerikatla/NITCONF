package com.example.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Submission;

/**
 * This interface represents a repository for managing submissions.
 */
@Repository
public interface SubmissionRepository extends CrudRepository<Submission, Integer> {

    /**
     * Finds a submission by its submission ID.
     *
     * @param submissionId the ID of the submission
     * @return an Optional containing the found submission, or an empty Optional if not found
     */
    Optional<Submission> findBysubmissionId(int submissionId);

    /**
     * Retrieves a list of all unreviewed submissions.
     *
     * @return a list of unreviewed submissions
     */
    List<Submission> findAllUnreviewedSubmissions();

    /**
     * Retrieves a list of all reviewed submissions.
     *
     * @return a list of reviewed submissions
     */
    List<Submission> findAllReviewedSubmissions();

    /**
     * Retrieves a list of submissions for a given paper ID.
     *
     * @param paperId the ID of the paper
     * @return a list of submissions for the paper
     */
    List<Submission> findSubmissionsForPaper(int paperId);

}
