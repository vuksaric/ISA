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
    @OneToMany(fetch=FetchType.LAZY)
    private List<Dermatologist> dermatologist;
    @OneToMany(fetch=FetchType.LAZY)
    private List<Pharmacist> pharmacists;
    private String name;
    private String address;
    private String description;
    @OneToMany(fetch=FetchType.LAZY)
    private List<Medicine> medicines;
    private float mark;
}
