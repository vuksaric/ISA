package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.Consultation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationDTO {
    private String pharmacist;
    private LocalDate date;
    private String time;
    private float mark;
    private float price;
    private float id;
    private boolean canBeCanceled;
    private String notes;
    private String medicine;
    private DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm"); //h:mm a

    public ConsultationDTO(Consultation consultation){
        this.pharmacist = consultation.getPharmacist().getFullName();
        this.date = consultation.getPeriod().getStart_date().toLocalDate();
        this.time = consultation.getPeriod().getStart_date().format(formatter1) + " - "
                + consultation.getPeriod().getEnd_date().format(formatter1);
        this.mark = consultation.getPharmacist().getMark();
        this.price = consultation.getPharmacy().getConsultationPrice();
        this.id = consultation.getId();
        if(consultation.getReport() != null)
        {
            this.notes = consultation.getReport().getInformation();
            this.medicine = consultation.getReport().getTherapy().getMedicine().getName();
        }
        if(consultation.getPeriod().getStart_date().isAfter(LocalDateTime.now().plusDays(1))){
            this.canBeCanceled= true;
        }
        else{
            this.canBeCanceled=false;
        }
    }
}
