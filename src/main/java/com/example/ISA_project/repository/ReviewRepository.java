package com.example.ISA_project.repository;

import com.example.ISA_project.model.Review;
import com.example.ISA_project.model.ReviewType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Review findByPatientIdAndObjectIdAndReviewType(int patientId, int objectId, ReviewType reviewType);
    Review findById(int id);
    Review save(Review review);
}

