package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Review;
import com.example.ISA_project.model.ReviewType;
import com.example.ISA_project.model.dto.ReviewDTO;
import com.example.ISA_project.repository.ReviewRepository;
import com.example.ISA_project.service.IReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewService implements IReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewDTO findReview(int patientId, int objectId, ReviewType reviewType) {
        Review review = reviewRepository.findByPatientIdAndObjectIdAndReviewType(patientId, objectId, reviewType);
        if(review!=null)
            return new ReviewDTO(review, true);
        else
            return new ReviewDTO(0,0,objectId, patientId, reviewType, false);
    }

    @Override
    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        Review review = new Review(reviewDTO);
        reviewRepository.save(review);
        return findReview(reviewDTO.getPatientId(), reviewDTO.getObjectId(), reviewDTO.getReviewType());
    }

    @Override
    public ReviewDTO editReview(ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(reviewDTO.getId());
        review.setMark(reviewDTO.getMark());
        reviewRepository.save(review);
        return findReview(reviewDTO.getPatientId(), reviewDTO.getObjectId(), reviewDTO.getReviewType());
    }


}
