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
    @OneToOne(fetch=FetchType.LAZY)
    private Period date;
    private float price;
    @OneToOne(fetch=FetchType.LAZY)
    private Dermatologist dermatologist;
    @OneToOne(fetch=FetchType.LAZY)
    private Patient patient;
    private List<Medicine> therapy;
}
