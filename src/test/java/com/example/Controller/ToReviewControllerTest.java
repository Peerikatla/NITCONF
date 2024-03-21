package com.example.Controller;

import com.example.Service.Toreviewservice;
import com.example.model.Paper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ToReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Toreviewservice toreviewservice;

    @InjectMocks
    private ToReviewController toReviewController;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPapersWithSubmissions() throws Exception {
        // Mock data
        Paper paper = new Paper();
        // Mock service behavior
        when(toreviewservice.getAllPapersWithSubmissions()).thenReturn(Collections.singletonList(paper));

        // Perform GET request to API endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/papers"))
                .andExpect(status().isOk());

        // Verify service method is called
        verify(toreviewservice, times(1)).getAllPapersWithSubmissions();
    }

    @SuppressWarnings("null")
    @Test
    public void testGetAllSubmissionInfo() throws Exception {
        // Mock submission information
        List<Map<String, Object>> submissionInfo = new ArrayList<>();
        Map<String, Object> submission1 = new HashMap<>();
        submission1.put("title", "linear regression");
        submission1.put( "status", "To Review");
        submission1.put( "revision status", 0);
        submission1.put("deadline", Date.valueOf(LocalDate.parse("2024-04-01")));
        submission1.put("link", "");
        submission1.put("submissionId", 4);
        submission1.put("paperId", 2);
        submissionInfo.add(submission1);

        // Mock toreviewservice response
        when(toreviewservice.getAllSubmissionInfo(2)).thenReturn(submissionInfo);

        // Perform GET request using mockMvc
        mockMvc.perform(get("/api/unreviewedsubmissions")
                .param("userId", "2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect( jsonPath("$[0].title").value(submission1.get("title")))
                .andExpect( jsonPath("$[0].status").value(submission1.get("status")))
                .andExpect(jsonPath("$[0].revision status").value(submission1.get("revision status")))
                .andExpect(jsonPath("$[0].deadline").value(submission1.get("deadline").toString()))
                .andExpect(jsonPath("$[0].link").value(submission1.get("link")))
                .andExpect(jsonPath("$[0].submissionId").value(submission1.get("submissionId")))
                .andExpect(jsonPath("[0].paperId").value(submission1.get("paperId")));
        // Add more assertions for other submission information if needed

        // Verify that toreviewservice method is called with the correct parameter
        verify(toreviewservice).getAllSubmissionInfo(1);
    }

    @Test
    public void testSaveComment() throws Exception {
        // Mock data
        int paperId = 2;
        int submissionId = 201;
        String comment = "Test comment";
        int originality = 5;
        int relevance = 5;
        int quality = 5;
        int technicalContentandAccuracy = 5;
        int significanceOfWork = 5;
        int appropriateForSAC = 5;

        // Perform PATCH request to API endpoint
        mockMvc.perform(MockMvcRequestBuilders
                .patch("/api/to-review/papers/{paperId}/submissions/{submissionId}/comment", paperId, submissionId)
                .param("comment", comment)
                .param("originality", String.valueOf(originality))
                .param("relevance", String.valueOf(relevance))
                .param("quality", String.valueOf(quality))
                .param("technicalContentandAccuracy", String.valueOf(technicalContentandAccuracy))
                .param("significanceOfWork", String.valueOf(significanceOfWork))
                .param("appropriateForSAC", String.valueOf(appropriateForSAC)))
                .andExpect(status().isOk());

        // Verify service method is called with correct arguments
        verify(toreviewservice, times(1)).saveComment(paperId, submissionId, comment, originality, relevance, quality,
                technicalContentandAccuracy, significanceOfWork, appropriateForSAC);
    }

    @Test
    public void testSaveCommentWithEmptyComment() throws Exception {
        // Mock data
        int paperId = 1;
        int submissionId = 1;
        String comment = ""; // Empty comment
        int originality = 5;
        int relevance = 5;
        int quality = 5;
        int technicalContentandAccuracy = 5;
        int significanceOfWork = 5;
        int appropriateForSAC = 5;

        // Perform PATCH request to API endpoint
        mockMvc.perform(MockMvcRequestBuilders
                .patch("/api/to-review/papers/{paperId}/submissions/{submissionId}/comment", paperId, submissionId)
                .param("comment", comment)
                .param("originality", String.valueOf(originality))
                .param("relevance", String.valueOf(relevance))
                .param("quality", String.valueOf(quality))
                .param("technicalContentandAccuracy", String.valueOf(technicalContentandAccuracy))
                .param("significanceOfWork", String.valueOf(significanceOfWork))
                .param("appropriateForSAC", String.valueOf(appropriateForSAC)))
                .andExpect(status().isOk());

        // Verify service method is not called
        verify(toreviewservice, never()).saveComment(paperId, submissionId, comment, originality, relevance, quality,
                technicalContentandAccuracy, significanceOfWork, appropriateForSAC);
    }
}
