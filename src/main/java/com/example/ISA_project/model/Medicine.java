package com.example.ISA_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Medicine {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    private String shape;
    @ElementCollection
    private List<String> ingredients;
    private String manifacturer;
    private IssuingMode issuingMode;
    @ElementCollection
    private List<Integer> replacementIds;
    private String notes;
}
