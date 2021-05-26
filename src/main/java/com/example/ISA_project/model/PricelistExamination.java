package com.example.ISA_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PricelistExamination {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch=FetchType.LAZY)
    private Examination examination;

    @OneToOne(fetch=FetchType.LAZY)
    private Pharmacy pharmacy;

    private float price;
    @OneToOne(fetch=FetchType.LAZY)
    private Period validity;
}
