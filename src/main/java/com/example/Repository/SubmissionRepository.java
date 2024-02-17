package com.example.Repository;

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
}
