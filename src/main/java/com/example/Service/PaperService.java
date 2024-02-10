package com.example.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Paper;
import com.example.model.Submission;

/**
* A service class for managing papers and their submissions.
*
* @author example
*/
public class PaperService{

   /**
    * A list of papers managed by this service.
    */
   private List<Paper> papers;

   /**
    * Retrieves a list of papers that have at least one submission.
    *
    * @return a list of papers with submissions
    */
   public List<Paper> getAllPapersWithSubmissions() {
       return papers;
   }

   /**
    * Saves a comment and rating for a specific submission within a paper.
    *
    * @param paperId the ID of the paper
    * @param submissionId the ID of the submission
    * @param comment the comment to save
    * @param rating the rating to save
    */
   public void saveComment(int paperId, int submissionId, String comment, int rating) {
       findPaperById(paperId).getSubmissions().stream()
               .filter(submission -> submission.getSubmissionId() == submissionId)
               .findFirst()
               .ifPresent(submission -> {
                   submission.setComment(comment);
                   submission.setRating(rating);
               });
   }

   /**
    * Finds a paper by its ID.
    *
    * @param paperId the ID of the paper
    * @return the paper with the given ID, or null if not found
    */
   private Paper findPaperById(int paperId) {
        return papers.stream()
                .filter(paper -> paper.getPaperId() == paperId)
                .findFirst()
                .orElse(null);       
   }

   

   /**
    * Retrieves a paper that has been reviewed.
    *
    * @return the reviewed paper, or null if not found
    */
   public Paper getReviewedPaper(int paperId) {
       return papers.stream()
               .filter(paper -> paper.getStatus() == 2)
               .findFirst()
               .orElse(null);
   }

}
