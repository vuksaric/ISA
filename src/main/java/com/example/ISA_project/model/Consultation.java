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
    //@ManyToOne()
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Period period;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Pharmacy pharmacy;
    private float price;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Pharmacist pharmacist;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient patient;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Report report;
    private boolean done;
}
