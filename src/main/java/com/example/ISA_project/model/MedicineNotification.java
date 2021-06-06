package com.example.ISA_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicineNotification {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Medicine medicine;
    private int pharmacy_id;

    public MedicineNotification(Medicine medicine, int pharmacy_id)
    {
        this.medicine = medicine;
        this.pharmacy_id = pharmacy_id;
    }

}
