package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.Medicine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineAllergyDTO {
    String name;

    public MedicineAllergyDTO(Medicine medicine){
        this.name = medicine.getName();
    }
}
