package com.example.ISA_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DermatologistFreePeriodsRequestDTO {
    String date;
    int pharmacyId;
    int dermatologistId;

    public DermatologistFreePeriodsRequestDTO(String date, int id1, int id2){
        this.date = date;
        this.pharmacyId = id1;
        this.dermatologistId = id2;
    }
}
