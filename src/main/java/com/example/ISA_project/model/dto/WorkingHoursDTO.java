package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.WorkingHours;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkingHoursDTO {
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int pharmacyId;
}
