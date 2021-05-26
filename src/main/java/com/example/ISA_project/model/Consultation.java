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
    @OneToOne(fetch=FetchType.LAZY)
    private Period period;
    @OneToOne(fetch=FetchType.LAZY)
    private Pharmacy pharmacy;
    private float price;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="Consultation_ID")
    private List<Medicine> therapy;
}
