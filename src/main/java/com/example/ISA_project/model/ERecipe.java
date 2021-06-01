package com.example.ISA_project.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ERecipe {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient patient;
    private LocalDate date;
    @OneToOne(fetch=FetchType.LAZY)
    private Pharmacy pharmacy;
    private ERecipeStatus eRecipeStatus;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="ERecipe_ID")
    List<MedicineQuantity> medicines;

}
