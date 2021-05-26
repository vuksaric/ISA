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
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="PatientChart_ID")
    private List<Medicine> allergies;
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="PatientChart_ID")
    private List<Examination> previousExaminations;
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="PatientChart_ID")
    private List<Consultation> previousConsultations;
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="PatientChart_ID")
    private List<Reservation> reservations;
}
