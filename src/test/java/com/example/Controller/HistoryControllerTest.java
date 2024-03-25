package com.example.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Repository.PaperRepository;
import com.example.Repository.SubmissionRepository;
import com.example.Repository.UserRepository;
import com.example.Service.HistoryService;
import com.example.model.Paper;
import com.example.model.Submission;
import com.example.model.User;

import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class HistoryControllerTest {

    /**
     * This class contains unit tests for the HistoryController class.
     */

    @Mock
    private HistoryService historyService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SubmissionRepository submissionRepository;

    @Mock
    private PaperRepository paperRepository;

    @InjectMocks
    private HistoryController HistoryController;

    private User user;
    private Paper paper;
    private Submission submission1, submission2;

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
        user.setPaperlimit(15L);
        user.setSpecialization("Machine learning");
        user.setDateOfBirth(LocalDate.of(2003, Month.SEPTEMBER, 8)); // Set date of birth

        // Set up the paper
        paper = new Paper();
        paper.setPaperId(8);
        paper.setTitle("svm");
        paper.setRevisionStatus(0);
        paper.setApprovestatus("Approved");
        paper.setTag("machine learning"); // Assuming a tag for the paper
        paper.setUser(user); // Set the user for the paper

        // Set up the submission
        submission1 = new Submission();
        submission1.setSubmissionId(27);
        submission1.setDeadline(LocalDate.of(2024, Month.MARCH, 01)); // Set deadline
        submission1.setStatus("reviewed");
        submission1.setLink("src/main/resources/Static/ml.pdf");
        submission1.setComment("Test Comment");
        submission1.setOriginality(3);
        submission1.setRelevance(4);
        submission1.setQuality(3);
        submission1.setTCA(3);
        submission1.setSignificanceOfWork(3);
        submission1.setAppropriateForSAC(3);
        submission1.setPaper(paper);

        submission2 = new Submission();
        submission2.setSubmissionId(27);
        submission2.setDeadline(LocalDate.of(2024, Month.MARCH, 20)); // Set deadline
        submission2.setStatus("reviewed");
        submission2.setLink("src/main/resources/Static/ml.pdf");
        submission2.setComment("Test Comment");
        submission2.setOriginality(5);
        submission2.setRelevance(4);
        submission2.setQuality(3);
        submission2.setTCA(4);
        submission2.setSignificanceOfWork(2);
        submission2.setAppropriateForSAC(4);
        submission2.setPaper(paper);
    }

    /**
     * Test case for the getAllHistoryPapers method.
     * 
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testGetAllHistory() throws Exception {
        List<Map<String, Object>> historyPapers = new ArrayList<>();
        Map<String, Object> paperMap = new HashMap<>();
        paperMap.put("title", paper.getTitle());
        paperMap.put("status", paper.getApprovestatus());
        paperMap.put("revisionStatus", paper.getRevisionStatus());
        paperMap.put("deadline", submission1.getDeadline());
        paperMap.put("paperId", paper.getPaperId());
        historyPapers.add(paperMap);

        when(historyService.getAllHistory(anyInt())).thenReturn(historyPapers);

        // Test
        ResponseEntity<List<Map<String, Object>>> response = HistoryController.getAllHistoryPapers(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
    }
    
    /**
     * Test case for the getAllHistoryPapers method when there is no data.
     * 
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testGetAllHistoryNoData() throws Exception {
        List<Map<String, Object>> historyPapers = new ArrayList<>();
        when(historyService.getAllHistory(anyInt())).thenReturn(historyPapers);

        // Test
        ResponseEntity<List<Map<String, Object>>> response = HistoryController.getAllHistoryPapers(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());        
    }

}
