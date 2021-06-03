package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.Pharmacist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacistDTO {
    private String name;
    private float grade;
    private int id;

    public PharmacistDTO(Pharmacist pharmacist){
        this.name=pharmacist.getFullName();
        this.grade= pharmacist.getMark();
        this.id = pharmacist.getId();
    }
}
