package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationRequestDTO {

    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private UserType user_type;
    private String pharmacy_name;
    private int user_id;
}
