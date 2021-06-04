package com.example.ISA_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationRequest {
    private String dateTime;
    private int pharmacyId;
    private int pharmacistId;
    private int patientId;

}
