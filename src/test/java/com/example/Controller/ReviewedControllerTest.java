package com.example.Controller;

import com.example.Service.Reviewedservice;
import com.example.model.Paper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@WebMvcTest(ReviewedController.class)
public class ReviewedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Reviewedservice reviewedservice;

    @InjectMocks
    private ReviewedController reviewedController;

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
        when(reviewedservice.getAllPapersWithSubmissions()).thenReturn(Collections.singletonList(paper));

        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/papers"))
                // Validate response status
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Validate response body
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(paper.getPaperId()));
    }

    @Test
    public void testGetPapersWithReviews() throws Exception {
        // Mock data
        int userId = 1;
        List<Map<String, Object>> papersWithReviews = Collections.singletonList(Collections.emptyMap());
        // Mock service behavior
        when(reviewedservice.getPapersWithReviews(userId)).thenReturn(papersWithReviews);

        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/papers/{userId}/reviews", userId))
                // Validate response status
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Validate response body
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists());
    }

    @Test
    public void testUpdateComment() throws Exception {
        // Mock data
        int paperId = 1;
        int submissionId = 1;
        String comment = "Test comment";
        int rating = 5;

        // Perform PATCH request
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/papers/{paperId}/submissions/{submissionId}/comment", paperId, submissionId)
                        .param("comment", comment)
                        .param("rating", String.valueOf(rating)))
                // Validate response status
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify service method is called with correct arguments
        verify(reviewedservice, times(1)).updatecomment(paperId, submissionId, comment, rating);
    }
}
