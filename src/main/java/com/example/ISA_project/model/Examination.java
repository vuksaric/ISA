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
    private String diagnosis;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="Examination_ID")
    private List<Medicine> therapy;
    private boolean free;
}
