package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.Review;
import com.example.ISA_project.model.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private int id;
    private int mark;
    private int objectId;
    private int patientId;
    private ReviewType reviewType;
    private Boolean reviewed;

    public ReviewDTO(Review review, Boolean reviewed){
        this.id = review.getId();
        this.mark = review.getMark();
        this.objectId = review.getObjectId();
        this.patientId = review.getPatientId();
        this.reviewType =review.getReviewType();
        this.reviewed = reviewed;
    }

}
