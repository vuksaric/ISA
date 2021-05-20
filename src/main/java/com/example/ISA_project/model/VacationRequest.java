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
    private int id;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private UserType user_type;
    private int user_id;

}
