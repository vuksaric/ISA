package com.example.ISA_project.service;

import com.example.ISA_project.model.ReviewType;
import com.example.ISA_project.model.dto.ReviewDTO;

public interface IReviewService {
    ReviewDTO findReview(int patientId, int objectId, ReviewType reviewType);
    ReviewDTO saveReview(ReviewDTO reviewDTO);
    ReviewDTO editReview(ReviewDTO reviewDTO);
}
