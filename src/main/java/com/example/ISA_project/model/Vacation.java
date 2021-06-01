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
public class Vacation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private UserType user_type;
    private int pharmacy_id;
    private int user_id;

    public Vacation(LocalDateTime start_date, LocalDateTime end_date, UserType user_type, int pharmacy_id, int user_id) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.user_type = user_type;
        this.pharmacy_id = pharmacy_id;
        this.user_id = user_id;
    }
}
