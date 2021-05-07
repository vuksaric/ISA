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
public class Pharmacist extends User{
    private float mark;
    @OneToMany(fetch=FetchType.LAZY)
    private List<WorkdayPharmacist> workdays;
}
