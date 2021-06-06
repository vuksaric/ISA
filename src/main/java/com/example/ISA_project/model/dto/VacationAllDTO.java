package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.VacationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationAllDTO {
    int id;
    int pharmacy_id;
    int user_id;
    LocalDateTime start_date;
    LocalDateTime end_date;
    String name;
    String surname;
    String user_type;

    public VacationAllDTO(VacationRequest vacationRequest,ProfileDTO profileDTO){
        this.start_date = vacationRequest.getStart_date();
        this.end_date = vacationRequest.getEnd_date();
        this.name = profileDTO.getName();
        this.surname = profileDTO.getSurname();
        this.user_type = profileDTO.getType();
        this.id = vacationRequest.getId();
        this.pharmacy_id = vacationRequest.getPharmacy_int();
        this.user_id = vacationRequest.getUser_id();
    }
}
