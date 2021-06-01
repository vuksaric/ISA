package com.example.ISA_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreviousConsultationDTO {

    private int user_id;
    private String name;
    private String surname;
    private String address;
    private LocalDateTime date;
    private String pharmacyName;

}
