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
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="Medicine_ID")
    private List<Ingredient> ingredients;
    private String manufacturer;
    private IssuingMode issuingMode;
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="Medicine_ID")
    private List<Medicine> replacements;
    private String notes;
}
