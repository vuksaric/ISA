package com.example.ISA_project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Reservation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String serialNumber;
    private LocalDateTime dueDate;
    @OneToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private Medicine medicine;
    @OneToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private Pharmacy pharmacy;
    @OneToOne(fetch= FetchType.LAZY)
    private Patient patient;
    private boolean issued;
    private boolean canceled;

    public Reservation(String serialNumber, LocalDateTime dueDate, Medicine medicine, Pharmacy pharmacy, Patient patient){
        this.serialNumber = serialNumber;
        this.dueDate=dueDate;
        this.medicine=medicine;
        this.pharmacy=pharmacy;
        this.patient=patient;
    }

}
