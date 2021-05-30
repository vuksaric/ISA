package com.example.ISA_project.controller;

import com.example.ISA_project.model.ReviewType;
import com.example.ISA_project.model.dto.ReviewDTO;
import com.example.ISA_project.service.IReviewService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final IReviewService reviewService;

    public ReviewController(IReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping(value="/find/{patientId}/{objectId}/{type}")
    public ResponseEntity<ReviewDTO> findReview(@PathVariable String patientId,@PathVariable String objectId,@PathVariable String type){
        int patient = Integer.parseInt(patientId);
        int object = Integer.parseInt(objectId);
        ReviewType reviewType = ReviewType.valueOf(type);
        return new ResponseEntity<>(reviewService.findReview(patient, object,reviewType ), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ReviewDTO> saveReview(@RequestBody ReviewDTO reviewDTO){
        return new ResponseEntity<>(reviewService.saveReview(reviewDTO), HttpStatus.OK);
    }
    @PutMapping(consumes = "application/json")
    public ResponseEntity<ReviewDTO> editReview(@RequestBody ReviewDTO reviewDTO){
        return new ResponseEntity<>(reviewService.editReview(reviewDTO), HttpStatus.OK);
    }
}
