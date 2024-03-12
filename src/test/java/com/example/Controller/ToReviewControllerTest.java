package com.example.Controller;

import com.example.Service.Toreviewservice;
import com.example.model.Paper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ToReviewControllerTest {

    @Mock
    private Toreviewservice toreviewservice;

    @InjectMocks
    private ToReviewController toReviewController;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test case for the getAllPapersWithSubmissions method of ToReviewController.
     * It verifies that the controller method returns the expected result and calls the service method.
     */
    @Test
    public void testGetAllPapersWithSubmissions() {
        // Mock data
        Paper paper = new Paper();
        // Mock service behavior
        when(toreviewservice.getAllPapersWithSubmissions()).thenReturn(Collections.singletonList(paper));

        // Call controller method
        List<Paper> result = toReviewController.getAllPapersWithSubmissions();

        // Verify service method is called
        verify(toreviewservice, times(1)).getAllPapersWithSubmissions();

        // Assert result
        assertEquals(1, result.size());
        assertEquals(paper, result.get(0));
    }

    /**
     * Test case for the getAllSubmissionInfo method of ToReviewController.
     * It verifies that the controller method returns the expected result and calls the service method.
     */
    @Test
    public void testGetAllSubmissionInfo() {
        // Mock data
        int userId = 1;
        List<Map<String, Object>> submissionInfoList = Collections.singletonList(Collections.emptyMap());
        // Mock service behavior
        when(toreviewservice.getAllSubmissionInfo(userId)).thenReturn(submissionInfoList);

        // Call controller method
        List<Map<String, Object>> result = toReviewController.getAllSubmissionInfo(userId);

        // Verify service method is called
        verify(toreviewservice, times(1)).getAllSubmissionInfo(userId);

        // Assert result
        assertEquals(1, result.size());
        assertEquals(Collections.emptyMap(), result.get(0));
    }

    /**
     * Test case for the saveComment method of ToReviewController.
     * It verifies that the controller method calls the service method with the correct arguments.
     */
    @Test
    public void testSaveComment() {
        // Mock data
        int paperId = 1;
        int submissionId = 1;
        String comment = "Test comment";
        int rating = 5;

        // Call controller method
        toReviewController.saveComment(paperId, submissionId, comment, rating);

        // Verify service method is called with correct arguments
        verify(toreviewservice, times(1)).saveComment(paperId, submissionId, comment, rating);
    }

    /**
     * Test case for the saveComment method of ToReviewController with a different rating.
     * It verifies that the controller method calls the service method with the correct arguments.
     */
    @Test
    public void testSaveCommentWithDifferentRating() {
        // Mock data
        int paperId = 1;
        int submissionId = 1;
        String comment = "Test comment";
        int rating = 3;

        // Call controller method
        toReviewController.saveComment(paperId, submissionId, comment, rating);

        // Verify service method is called with correct arguments
        verify(toreviewservice, times(1)).saveComment(paperId, submissionId, comment, rating);
    }

    /**
     * Test case for the saveComment method of ToReviewController with an empty comment.
     * It verifies that the service method is not called.
     */
    @Test
    public void testSaveCommentWithEmptyComment() {
        // Mock data
        int paperId = 1;
        int submissionId = 1;
        String comment = ""; // Empty comment
        int rating = 5;

        // Call controller method
        toReviewController.saveComment(paperId, submissionId, comment, rating);

        // Verify service method is not called
        verify(toreviewservice, never()).saveComment(anyInt(), anyInt(), anyString(), anyInt());
    }
}
