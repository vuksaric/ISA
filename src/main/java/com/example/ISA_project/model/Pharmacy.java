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
public class Pharmacy {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "Pharmacy_Dermatologist",
            joinColumns = { @JoinColumn(name = "Pharmacy_ID") },
            inverseJoinColumns = { @JoinColumn(name = "Dermatologist_ID") })
    private List<Dermatologist> dermatologist;
    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="PharmacyList_ID")
    private List<Pharmacist> pharmacists;
    private String name;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;
    private String description;
    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="Pharmacy_ID")
    private List<MedicineQuantity> medicines; // lekovi koji su bazi apoteke
    private float mark;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="Pharmacy_ID")
    private List<Action> actions;
    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "Pharmacy_Patient",
            joinColumns = { @JoinColumn(name = "Pharmacy_ID") },
            inverseJoinColumns = { @JoinColumn(name = "Patient_ID") })
    private List<Patient> subscribers;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="Pharmacy_ID")
    private List<MedicinePoints> points;
    private float consultationPrice;
}
