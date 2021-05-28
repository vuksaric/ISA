package com.example.ISA_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    private String serialNumber;
    private LocalDateTime dueDate;
    private int idPatient;
    private int idMedicine;
    private int idPharmacy;
}
