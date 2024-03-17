package com.example.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service.HistoryService;


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
        return new ResponseEntity<>(historyPapers, HttpStatus.OK);
    }
    
}
