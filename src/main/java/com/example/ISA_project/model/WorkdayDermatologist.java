package com.example.ISA_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="WorkdayDermatologist_ID")
    private List<Examination> examinations;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="WorkdayDermatologist_ID")
    private List<Period> freeTerms; // brisanje
    /*@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="Dermatologist_ID")
    private List<WorkingHours> workingHours;*/
    private LocalDate date;

}
