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

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
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

    @SuppressWarnings("null")
    @Test
    public void testGetAllPapersWithSubmissions() throws Exception {
        // Mock data
        Paper paper = new Paper();
        // Mock service behavior
        when(toreviewservice.getAllPapersWithSubmissions()).thenReturn(Collections.singletonList(paper));

        // Perform GET request to API endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/papers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(paper.getPaperId()))
                .andExpect(jsonPath("$[0].title").value(paper.getTitle()));

        // Verify service method is called
        verify(toreviewservice, times(1)).getAllPapersWithSubmissions();
    }

    @SuppressWarnings("null")
    @Test
    public void testGetAllSubmissionInfo() throws Exception {
        // Mock data
        int userId = 1;
        List<Map<String, Object>> submissionInfoList = Collections.singletonList(Collections.emptyMap());
        // Mock service behavior
        when(toreviewservice.getAllSubmissionInfo(userId)).thenReturn(submissionInfoList);

        // Perform GET request to API endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/submissions/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").value(Collections.emptyMap()));

        // Verify service method is called
        verify(toreviewservice, times(1)).getAllSubmissionInfo(userId);
    }

    @Test
    public void testSaveComment() throws Exception {
        // Mock data
        int paperId = 2;
        int submissionId = 201;
        String comment = "Test comment";
        int rating = 5;

        // Perform PATCH request to API endpoint
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/to-review/papers/{paperId}/submissions/{submissionId}/comment", paperId, submissionId)
                .param("comment", comment)
                .param("rating", String.valueOf(rating)))
                .andExpect(status().isOk());

        // Verify service method is called with correct arguments
        verify(toreviewservice, times(1)).saveComment(paperId, submissionId, comment, rating);
    }

    @Test
    public void testSaveCommentWithEmptyComment() throws Exception {
        // Mock data
        int paperId = 1;
        int submissionId = 1;
        String comment = ""; // Empty comment
        int rating = 5;

        // Perform PATCH request to API endpoint
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/to-review/papers/{paperId}/submissions/{submissionId}/comment", paperId, submissionId)
                .param("comment", comment)
                .param("rating", String.valueOf(rating)))
                .andExpect(status().isOk());

        // Verify service method is not called
        verify(toreviewservice, never()).saveComment(paperId, submissionId, comment, rating);
    }
}
