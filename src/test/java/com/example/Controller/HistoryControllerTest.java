package com.example.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Service.HistoryService;

public class HistoryControllerTest {

    @Mock
    private HistoryService historyService;

    @InjectMocks
    private HistoryController historyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        // Mock behavior of HistoryService
        List<Map<String, Object>> historyPapers = new ArrayList<>();
        
        // First history paper
        Map<String, Object> paperMap1 = new HashMap<>();
        paperMap1.put("title", "svm");
        paperMap1.put("status", "Approved");
        paperMap1.put("revisionStatus", 0);
        paperMap1.put("deadline", LocalDate.of(2024, Month.MARCH, 1));
        paperMap1.put("paperId", 8);
        historyPapers.add(paperMap1);
        
        // Second history paper
        Map<String, Object> paperMap2 = new HashMap<>();
        paperMap2.put("title", "neural networks");
        paperMap2.put("status", "Rejected");
        paperMap2.put("revisionStatus", 1);
        paperMap2.put("deadline", LocalDate.of(2024, Month.MARCH, 10));
        paperMap2.put("paperId", 9);
        historyPapers.add(paperMap2);
        
        when(historyService.getAllHistory(anyInt())).thenReturn(historyPapers);
    }

    /**
     * Test case for the getAllHistoryPapers method.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testGetAllHistory() throws Exception {
        // Test
        ResponseEntity<List<Map<String, Object>>> response = historyController.getAllHistoryPapers(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        List<Map<String, Object>> historyPapers = response.getBody();
        assertEquals(2, historyPapers.size());
        
        
    }

    /**
     * Test case for the getAllHistoryPapers method when there is no data.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testGetAllHistoryNoData() throws Exception {
        // Mock behavior of HistoryService returning empty list
        when(historyService.getAllHistory(anyInt())).thenReturn(new ArrayList<>());

        // Test
        ResponseEntity<List<Map<String, Object>>> response = historyController.getAllHistoryPapers(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}