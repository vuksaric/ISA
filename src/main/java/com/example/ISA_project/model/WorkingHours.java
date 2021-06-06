package com.example.ISA_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkingHours {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalTime startTime;
    private LocalTime endTime;
    @OneToOne(fetch= FetchType.LAZY)
    private Pharmacy pharmacy;

    public WorkingHours(LocalTime start, LocalTime end, Pharmacy pharmacy) {
        this.startTime = start;
        this.endTime = end;
        this.pharmacy = pharmacy;
    }
}
