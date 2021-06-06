package com.example.ISA_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewExaminationDTO {
    int pharmacyId;
    int dermatologistId;
    String date;
    String start;
    String end;
    float price;
}
