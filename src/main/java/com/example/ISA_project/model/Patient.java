package com.example.ISA_project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @CollectionTable(name="penaltyPoints", joinColumns=@JoinColumn(name="patient_id"))
    @Column(name="penaltyDate")
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<LocalDate> penaltyPoints;
    //@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name="patient_id")
    //private List<PenaltyPoints> penaltyPoints;
    private int loyaltyPoints;
    private PatientCategory patientCategory;
}
