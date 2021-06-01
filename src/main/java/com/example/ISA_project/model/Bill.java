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
public class Bill {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private Medicine medicine;
    @OneToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private Pharmacy pharmacy;
    private float price;
    private LocalDateTime date;
}
