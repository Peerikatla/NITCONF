//
//package com.example.Controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.example.Service.PaperService;
//import com.example.model.Paper;
//
///**
// * The PaperController class handles requests related to paper management and review.
// */
//@Controller
//@RequestMapping("/paper")
//public class PaperController {
//
//    private final PaperService paperService;
//
//    /**
//     * Constructs a PaperController with the specified PaperService.
//     *
//     * @param paperService The PaperService used for handling paper-related operations.
//     */
//    public PaperController(PaperService paperService) {
//        this.paperService = paperService;
//    }
//
//    /**
//     * Handles the request to retrieve a reviewed paper.
//     *
//     * @param model The Spring Model to add attributes.
//     * @return The view name for displaying the reviewed paper (in this case, "reviewed_paper").
//     */
//    @GetMapping("/reviewed")
//    public String getReviewedPaper(Model model) {
//        // Implement the getReviewedPaper method in PaperService
//        Paper paper = paperService.getReviewedPaper();
//        model.addAttribute("paper", paper);
//        return "reviewed_paper";
//    }
//
//    // Add other mappings as needed
//
//}
