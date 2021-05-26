package com.example.ISA_project.model.dto;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private int id;
    private LocalDateTime start;
    private LocalDateTime end;
    private String pharmacyName;
    private String fullName;
}
