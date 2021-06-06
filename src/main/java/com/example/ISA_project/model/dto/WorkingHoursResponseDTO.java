package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.WorkingHours;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkingHoursResponseDTO {
    private int id;
    private String startTime;
    private String endTime;
    private int pharmacyId;

    public WorkingHoursResponseDTO(WorkingHours workingHours){
        this.startTime = workingHours.getStartTime().toString();
        this.endTime = workingHours.getEndTime().toString();
    }

    public WorkingHoursResponseDTO(LocalTime start, LocalTime end) {
        this.startTime = start.toString();
        this.endTime = end.toString();
    }
}
