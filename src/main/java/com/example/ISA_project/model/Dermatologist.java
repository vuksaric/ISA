package com.example.ISA_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Dermatologist{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="Dermatologist_ID")
    private List<WorkdayDermatologist> workdays;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="Dermatologist_ID")
    private List<WorkingHours> workingHours;
    private float mark;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="DermatologistVacation_ID")
    private List<Vacation> vacation;

    public String getFullName(){
        return user.getName() + " "+ user.getSurname();
    }
}
