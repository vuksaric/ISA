package com.example.ISA_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Report {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String information;
    @OneToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private Therapy therapy;

    public Report(String information, Therapy therapy)
    {
        this.information = information;
        this.therapy = therapy;
    }

}

