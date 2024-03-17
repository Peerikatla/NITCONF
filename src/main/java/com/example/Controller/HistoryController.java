package com.example.Controller;

import com.example.Service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * This class represents the controller for handling requests related to
 * reviewed papers.
 */
@RestController
@RequestMapping("/api")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    /**
     * @param userId
     * @return
     */
    @GetMapping("/outdatedpapers")
    public ResponseEntity<List<Map<String, Object>>> getAllHistoryPapers(@PathVariable Integer userId) {
        List<Map<String, Object>> historyPapers = historyService.getAllHistory(userId);
        return ResponseEntity.ok(historyPapers);
    }
    
}
