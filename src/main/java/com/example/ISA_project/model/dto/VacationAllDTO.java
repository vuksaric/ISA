package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.User;
import com.example.ISA_project.model.UserType;
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
    UserType user_type;

    public VacationAllDTO(VacationRequest vacationRequest, User user){
        this.start_date = vacationRequest.getStart_date();
        this.end_date = vacationRequest.getEnd_date();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.user_type = user.getUserType();
        this.id = vacationRequest.getId();
        this.pharmacy_id = vacationRequest.getPharmacy_int();
        this.user_id = vacationRequest.getUser_id();
    }
}
