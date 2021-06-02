package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.Examination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationDTO {
    private String dermatologist;
    private String date;
    private String time;
    private float mark;
    private float price;
    private float id;
    private boolean canBeCanceled;
    private String diagnosis;
    private String notes;
    private String medicine;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm"); //h:mm a

    public ExaminationDTO(Examination examination){
       this.dermatologist = examination.getDermatologist().getFullName();
       this.date = examination.getDate().getStart_date().format(formatter);
       this.time = examination.getDate().getStart_date().format(formatter1) + " - "
               + examination.getDate().getEnd_date().format(formatter1);
       this.mark = examination.getDermatologist().getMark();
       this.price = examination.getPrice();
       this.id = examination.getId();
       this.diagnosis = examination.getDiagnosis();
       this.notes = examination.getReport().getInformation();
       this.medicine = examination.getReport().getTherapy().getMedicine().getName();

       if(examination.getDate().getStart_date().isAfter(LocalDateTime.now().plusDays(1))){
           this.canBeCanceled= true;
        }
       else{
           this.canBeCanceled=false;
       }
    }
}
