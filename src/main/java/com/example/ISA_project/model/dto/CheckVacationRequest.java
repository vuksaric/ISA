package com.example.ISA_project.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckVacationRequest {

    private int id;
    private LocalDate date;
    private int pharmacyId;

}
