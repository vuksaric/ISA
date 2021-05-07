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
public class WorkdayDermatologist {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch=FetchType.LAZY)
    private Period period;
    @OneToMany(fetch=FetchType.LAZY)
    private List<Examination> examinations;
    @OneToMany(fetch=FetchType.LAZY)
    private List<Period> freeTerms;
}
