package com.example.ISA_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionDTO {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String text;
    private int pharmacyId;
}
