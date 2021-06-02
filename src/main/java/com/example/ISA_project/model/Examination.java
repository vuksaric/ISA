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
public class Examination {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Period date;
    private float price;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Dermatologist dermatologist;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient patient;
    private String diagnosis;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Report report;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Pharmacy pharmacy;
    private boolean free;
    private boolean done;

    public Examination(Period period,Pharmacy pharmacy,Dermatologist dermatologist,Patient patient, float price)
    {
        this.date = period;
        this.pharmacy = pharmacy;
        this.dermatologist = dermatologist;
        this.patient = patient;
        this.price = price;
    }
}
