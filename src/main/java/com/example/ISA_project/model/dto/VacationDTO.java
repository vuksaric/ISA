package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationDTO {
    private int requestId;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private UserType user_type;
    private int pharmacy_id;    // iz local storage-a
    private int user_id;        // pharmacist ili dermatologist id
    private String rejectionNote;
}
