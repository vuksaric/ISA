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
public class PatientChart {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "PatientChart_Allergy",
            joinColumns = { @JoinColumn(name = "PatientChart_ID") },
            inverseJoinColumns = { @JoinColumn(name = "Allergy_ID") })
    private List<Medicine> allergies;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="PatientChart_ID")
    private List<Examination> previousExaminations;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="PatientChart_ID")
    private List<Consultation> previousConsultations;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="PatientChart_ID")
    private List<Reservation> reservations;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="PatientChartFuture_ID")
    private List<Examination> futureExaminations;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="PatientChartFuture_ID")
    private List<Consultation> futureConsultations;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="PatientChart_ID")
    private List<ERecipe> eRecipes;

}
