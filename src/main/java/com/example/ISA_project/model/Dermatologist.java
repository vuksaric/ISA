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
public class Dermatologist extends User{
    @OneToMany(fetch=FetchType.LAZY)
    private List<WorkdayDermatologist> workdays;
    private float mark;
}
