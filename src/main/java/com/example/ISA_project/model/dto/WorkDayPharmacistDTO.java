package com.example.ISA_project.model.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkDayPharmacistDTO {

    private LocalDateTime date;
    private List<ConsultationDTO> consultations;

}
