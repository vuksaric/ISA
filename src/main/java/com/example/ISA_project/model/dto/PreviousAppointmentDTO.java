package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.Examination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreviousAppointmentDTO {

    private int user_id;
    private int patient_id;
    private String name;
    private String surname;
    private String address;
    private LocalDateTime date;
    private String pharmacyName;

    public PreviousAppointmentDTO(Examination examination)
    {
        user_id = examination.getPatient().getUser().getId();
        patient_id = examination.getPatient().getId();
        name = examination.getPatient().getUser().getName();
        surname = examination.getPatient().getUser().getSurname();
        address = examination.getPatient().getUser().getAddress().getFullAdress();
        date = examination.getDate().getStart_date();
        pharmacyName = examination.getPharmacy().getName();
    }

}
