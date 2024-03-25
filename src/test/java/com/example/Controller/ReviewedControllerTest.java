package com.example.Controller;

import com.example.Repository.SubmissionRepository;
import com.example.Service.Reviewedservice;
import com.example.model.Paper;
import com.example.model.Submission;
import com.example.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ReviewedControllerTest {

    @Mock
    private Reviewedservice reviewedservice;

    @Mock
    private SubmissionRepository submissionRepository;

    @InjectMocks
    private ReviewedController reviewedController;

    private Paper paper;
    private User user;
    private Submission submission1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserid(1);
        user.setEmail("gayatripeerikatla@gmil.com");
        user.setFullName("Gayatri P");
        user.setUsername("gayatri");
        user.setNumber("8688469868");
        user.setPassword("me@1");
        user.setSpecialization("Machine learning");
        user.setDateOfBirth(LocalDate.of(2003, Month.SEPTEMBER, 8)); // Set date of birth
        
        // Set up the paper
        paper = new Paper();
        paper.setPaperId(8);
        paper.setTitle("svm");
        paper.setRevisionStatus(0); 
        paper.setApprovestatus(null); // Assuming initial approval status as "Pending"
        paper.setTag("machine learning"); // Assuming a tag for the paper
        paper.setUser(user); // Set the user for the paper

        // Set up the submission
        submission1 = new Submission();
        submission1.setSubmissionId(27);
        submission1.setDeadline(LocalDate.of(2024, Month.APRIL, 20)); // Set deadline
        submission1.setStatus("toreview");
        submission1.setLink("");
        submission1.setComment("Brilliant work!");
        submission1.setOriginality(4);
        submission1.setRelevance(3);
        submission1.setQuality(5);
        submission1.setTCA(5);
        submission1.setSignificanceOfWork(3);
        submission1.setAppropriateForSAC(4);
        submission1.setPaper(paper);
    }

    @Test
    void testGetAllPapersWithSubmissions() {
        // Mock data
        List<Paper> papers = new ArrayList<>();
        papers.add(paper);
        when(reviewedservice.getAllPapersWithSubmissions()).thenReturn(papers);

        // Test
        ResponseEntity<List<Paper>> responseEntity = reviewedController.getAllPapersWithSubmissions();

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(papers, responseEntity.getBody());
    }

    @Test
    void testGetPapersWithReviews() {
        // Mock data
        List<Map<String, Object>> papersWithReviews = new ArrayList<>();
        Map<String, Object> paperMap = new HashMap<>();
        paperMap.put("submissionId", submission1.getSubmissionId());
        paperMap.put("paperId", paper.getPaperId());
        paperMap.put("title", paper.getTitle());
        paperMap.put("status", "Reviewed");
        paperMap.put("comment", submission1.getComment());
        paperMap.put("originality", submission1.getOriginality());
        paperMap.put("relevance", submission1.getRelevance());
        paperMap.put("quality", submission1.getQuality());
        paperMap.put("TCA", submission1.getTCA());
        paperMap.put("significanceOfWork", submission1.getSignificanceOfWork());
        paperMap.put("appropriateForSAC", submission1.getAppropriateForSAC());
        papersWithReviews.add(paperMap);

        when(reviewedservice.getPapersWithReviews(1)).thenReturn(papersWithReviews);

        // Test
        ResponseEntity<List<Map<String, Object>>> responseEntity = reviewedController.getPapersWithReviews(1);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(papersWithReviews, responseEntity.getBody());
    }
    @Test
    void testGetSubmissionDetails() {
        // Mock data
        Map<String, Object> submissionDetails = new HashMap<>();
        submissionDetails.put("submissionId", submission1.getSubmissionId());
        submissionDetails.put("comment", submission1.getComment());
        submissionDetails.put("originality", submission1.getOriginality());
        submissionDetails.put("relevance", submission1.getRelevance());
        submissionDetails.put("quality", submission1.getQuality());
        submissionDetails.put("TCA", submission1.getTCA());
        submissionDetails.put("significanceOfWork", submission1.getSignificanceOfWork());
        submissionDetails.put("appropriateForSAC", submission1.getAppropriateForSAC());

        when(reviewedservice.getSubmissionDetails(1)).thenReturn(submissionDetails);

        // Test
        ResponseEntity<Map<String, Object>> responseEntity = reviewedController.getSubmissionDetails(1);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(submissionDetails, responseEntity.getBody());
    }
    @Test
    void testSaveComment() {
        // Mock data
        ResponseEntity<Void> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("paperId", "2");
        requestBody.put("submissionId", "12");
        requestBody.put("comment", "Test comment");
        requestBody.put("originality", "5");
        requestBody.put("relevance", "4");
        requestBody.put("quality", "3");
        requestBody.put("TCA", "2");
        requestBody.put("significanceOfWork", "4");
        requestBody.put("appropriateForSAC", "5");

        // Mock behavior
        doNothing().when(reviewedservice).updatecomment(anyInt(), anyInt(), anyString(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt());

        // Test
        ResponseEntity<Void> responseEntity = reviewedController.saveComment(requestBody);

        // Verify
        assertEquals(expectedResponse.getStatusCode(), responseEntity.getStatusCode());
    }
    @Test
    void testUpdateCommentAndRating() {
        // Mock data
        ResponseEntity<Void> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("paperId", "2");
        requestBody.put("submissionId", "12");
        requestBody.put("comment", "Test comment");
        requestBody.put("originality", "5");
        requestBody.put("relevance", "4");
        requestBody.put("quality", "3");
        requestBody.put("TCA", "2");
        requestBody.put("significanceOfWork", "4");
        requestBody.put("appropriateForSAC", "5");

        // Mock behavior
        doNothing().when(reviewedservice).updatecomment(anyInt(), anyInt(), anyString(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt());

        // Test
        ResponseEntity<Void> responseEntity = reviewedController.saveComment(requestBody);

        // Verify
        assertEquals(expectedResponse.getStatusCode(), responseEntity.getStatusCode());
    }
    @Test
    void testUpdateCommentAndRatingWithInvalidRating() {
        // Mock data
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("paperId", "2");
        requestBody.put("submissionId", "12");
        requestBody.put("comment", "Test comment");
        requestBody.put("originality", "6"); // Invalid rating
        requestBody.put("relevance", "4");
        requestBody.put("quality", "3");
        requestBody.put("TCA", "2");
        requestBody.put("significanceOfWork", "4");
        requestBody.put("appropriateForSAC", "5");       

        // Test
        ResponseEntity<Void> responseEntity = reviewedController.saveComment(requestBody);

        // Verify
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
    @Test
    void testUpdateCommentAndRatingWithEmptyComment() {
        // Mock data
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("paperId", "2");
        requestBody.put("submissionId", "12");
        requestBody.put("comment", ""); // Empty comment
        requestBody.put("originality", "4");
        requestBody.put("relevance", "3");
        requestBody.put("quality", "5");
        requestBody.put("TCA", "5");
        requestBody.put("significanceOfWork", "3");
        requestBody.put("appropriateForSAC", "4");

        // Test
        ResponseEntity<Void> responseEntity = reviewedController.saveComment(requestBody);

        // Verify
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }


}