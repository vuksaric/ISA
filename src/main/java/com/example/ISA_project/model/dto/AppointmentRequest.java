package com.example.ISA_project.model.dto;


import com.example.ISA_project.model.Period;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {

    private Period period;
    private int consultationId;
}
