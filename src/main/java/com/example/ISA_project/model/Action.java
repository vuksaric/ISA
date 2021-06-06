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
public class Action {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String text;

    public Action(LocalDateTime startDate, LocalDateTime endDate, String text){
        this.startDate = startDate;
        this.endDate = endDate;
        this.text = text;
    }
}
