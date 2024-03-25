package com.example.Controller;

import com.example.Repository.SubmissionRepository;
import com.example.Service.Toreviewservice;
import com.example.model.Paper;
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

import com.example.Repository.PaperRepository;
import com.example.Repository.UserRepository;
import com.example.model.Submission;
import com.example.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ToReviewControllerTest {

	@Mock
    private Toreviewservice toreviewservice;
    
    @Mock
    private UserRepository userRepository;

    @Mock
    private SubmissionRepository submissionRepository;
    
    @Mock
    private PaperRepository paperRepository;
    
    @InjectMocks
    private ToReviewController toReviewController;

    private User user;
    private Paper paper;
    private Submission submission1;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        
        // Set up the user
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
        paper.setApprovestatus(null); 
        paper.setTag("Machine learning"); // Assuming a tag for the paper
        paper.setUser(user); // Set the user for the paper

        // Set up the submission
        submission1 = new Submission();
        submission1.setSubmissionId(27);
        submission1.setDeadline(LocalDate.of(2024, Month.APRIL, 20)); // Set deadline
        submission1.setStatus("toreview");
        submission1.setLink("src/main/resources/Static/ml.pdf");
        submission1.setComment(null);
        submission1.setOriginality(null);
        submission1.setRelevance(null);
        submission1.setQuality(null);
        submission1.setTCA(null);
        submission1.setSignificanceOfWork(null);
        submission1.setAppropriateForSAC(null);
        submission1.setPaper(paper);
    }


    @Test
    void testGetAllPapersWithSubmissions() {
        // Mock data
        List<Paper> papers = new ArrayList<>();
        papers.add(paper);
        when(toreviewservice.getAllPapersWithSubmissions()).thenReturn(papers);

        // Test
        ResponseEntity<List<Paper>> responseEntity = toReviewController.getAllPapersWithSubmissions();

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(papers, responseEntity.getBody());
    }

    @Test
    void testGetAllSubmissionInfo() {
        // Mock data
        List<Map<String, Object>> submissionInfo = new ArrayList<>();
        Map<String, Object> submissionMap = new HashMap<>();
        submissionMap.put("title", paper.getTitle());
        submissionMap.put("status", submission1.getStatus());
        submissionMap.put("revisionStatus", paper.getRevisionStatus());
        submissionMap.put("deadline", submission1.getDeadline());
        submissionMap.put("link", submission1.getLink());
        submissionMap.put("submissionId", submission1.getSubmissionId());
        submissionMap.put("paperId", paper.getPaperId());
        submissionInfo.add(submissionMap);

        when(toreviewservice.getAllSubmissionInfo(1)).thenReturn(submissionInfo);

        // Test
        ResponseEntity<List<Map<String, Object>>> responseEntity = toReviewController.getAllSubmissionInfo(1);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(submissionInfo, responseEntity.getBody());
    }


    @Test
    void testSaveComment() {
        // Mock data
        ResponseEntity<Void> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("paperId", "2");
        requestBody.put("submissionId", "12");
        requestBody.put("comment", "Good WOrk!");
        requestBody.put("originality", "4");
        requestBody.put("relevance", "4");
        requestBody.put("quality", "3");
        requestBody.put("TCA", "5");
        requestBody.put("significanceOfWork", "3");
        requestBody.put("appropriateForSAC", "5");

        // Mock behavior
        doNothing().when(toreviewservice).saveComment(anyInt(), anyInt(), anyString(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt());

        // Test
        ResponseEntity<Void> responseEntity = toReviewController.saveComment(requestBody);
        
        //System.out.println("Response: " + responseEntity);


        // Verify
        assertEquals(expectedResponse.getStatusCode(), responseEntity.getStatusCode());
    }

}
