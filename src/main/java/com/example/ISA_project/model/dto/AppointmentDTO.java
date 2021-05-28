package com.example.ISA_project.model.dto;


import java.time.LocalDateTime;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Examination;
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
    private boolean done;

    public AppointmentDTO(Consultation consultation)
    {
        this.id = consultation.getId();
        this.start = consultation.getPeriod().getStart_date();
        this.end = consultation.getPeriod().getEnd_date();
        this.pharmacyName = consultation.getPharmacy().getName();
        this.fullName = consultation.getPatient().getUser().getFullName();
        this.done = consultation.isDone();
    }

    public AppointmentDTO(Examination examination)
    {
        this.id = examination.getId();
        this.start = examination.getDate().getStart_date();
        this.end = examination.getDate().getEnd_date();
        this.pharmacyName = examination.getPharmacy().getName();
        this.fullName = examination.getPatient().getUser().getFullName();
        this.done = examination.isDone();
    }

}
