package com.example.Controller;

import com.example.Service.HistoryService;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private HistoryService historyService;

    @InjectMocks
    private HistoryController historyController;
   
    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @SuppressWarnings("null")
    @Test
    public void testGetAllHistory() throws Exception {
        // Mock data
        int userId = 1;
        //List<Map<String, Object>> history = Collections.singletonList(createPaperMap("Paper Title", "Approved", "Under Review"));
        
        List<Map<String, Object>> history = Collections.singletonList(Collections.emptyMap());
     // Mock service behavior
        when(historyService.getAllHistory(userId)).thenReturn(Collections.singletonList(Collections.emptyMap()));
        
        // Perform GET request to API endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/outdatedpapers", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Paper Title"))
                .andExpect(jsonPath("$[0].status").value("Approved"))
                .andExpect(jsonPath("$[0].revisionStatus").value("Under Review"));

        // Verify service method is called
        verify(historyService, times(1)).getAllHistory(userId);
    }
    
    @SuppressWarnings("null")
    @Test
    public void testGetAllHistoryNoData() throws Exception {
        // Mock data
        int userId = 1;
        // Mock service behavior
        when(historyService.getAllHistory(userId)).thenReturn(Collections.emptyList());

        // Perform GET request to API endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/outdatedpapers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty()); // Expecting an empty array

        // Verify service method is called
        verify(historyService, times(1)).getAllHistory(userId);
    }

    // Helper method to create a paper map for testing
    private Map<String, Object> createPaperMap(String title, String status, String revisionStatus) {
        Map<String, Object> paperMap = new HashMap<>();
        paperMap.put("title", title);
        paperMap.put("status", status);
        paperMap.put("revisionStatus", revisionStatus);
        // Assuming deadline is not needed for this test case
        return paperMap;
    }
}
