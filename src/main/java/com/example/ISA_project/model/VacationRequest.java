package com.example.ISA_project.model;

import java.time.LocalDateTime;
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
public class VacationRequest {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private UserType user_type;
    private int pharmacy_int;
    private int user_id;

    public VacationRequest(LocalDateTime start_date, LocalDateTime end_date, UserType user_type, int pharmacy_int, int user_id)
    {
        this.start_date = start_date;
        this.end_date = end_date;
        this.user_type = user_type;
        this.pharmacy_int = pharmacy_int;
        this.user_id = user_id;
    }

}
