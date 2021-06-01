package com.example.ISA_project.model;

import com.example.ISA_project.model.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Review {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int mark;
    private int objectId; // id onoga za sta je ocena vezana
    private int patientId;
    private ReviewType reviewType;

    public Review(ReviewDTO reviewDTO){
        this.mark=reviewDTO.getMark();
        this.objectId = reviewDTO.getObjectId();
        this.patientId = reviewDTO.getPatientId();
        this.reviewType = reviewDTO.getReviewType();
    }
}
