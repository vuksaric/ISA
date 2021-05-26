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
    private Therapy therapy;
    private boolean free;
    private boolean done;
}
