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
public class Consultation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Period period;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Pharmacy pharmacy;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Pharmacist pharmacist;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient patient;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Report report;
    private boolean done;

    public Consultation(Period period,Pharmacy pharmacy,Pharmacist pharmacist,Patient patient)
    {
        this.period = period;
        this.pharmacy = pharmacy;
        this.pharmacist = pharmacist;
        this.patient = patient;
    }
}
