package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.Medicine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequest {

    private String id;
    private String information;
    private Medicine medicine;
    private int duration;
    private String diagnosis;


}

