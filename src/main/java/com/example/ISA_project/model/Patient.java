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
public class Patient{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private PatientChart patientChart;
    @ElementCollection
    @CollectionTable(name="penaltyDates", joinColumns=@JoinColumn(name="patient_id"))
    @Column(name="penaltyDate")
    private List<LocalDate> penaltyPoints;
    private int loyaltyPoints;
    private PatientCategory patientCategory;
}
