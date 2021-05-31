package com.example.ISA_project.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkDayDTO {

    private int id;
    private LocalDate date;
    private List<AppointmentDTO> consultations;

}
