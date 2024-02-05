/*package com.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Service.PaperService;
import com.example.model.Paper;

@Controller
@RequestMapping("/paper")
public class PaperController {

    private final PaperService paperService;

    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @GetMapping("/reviewed")
    public String getReviewedPaper(Model model) {
        Paper paper = paperService.getReviewedPaper(); // Implement this method in PaperService
        model.addAttribute("paper", paper);
        return "reviewed_paper";
    }

    // Add other mappings as needed
}*/
