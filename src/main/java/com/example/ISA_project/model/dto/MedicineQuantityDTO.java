package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.MedicineQuantity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineQuantityDTO {
    private String medicine;
    private int quantity;

    public MedicineQuantityDTO(MedicineQuantity medicineQuantity){
        this.medicine = medicineQuantity.getMedicine().getMedicineInformation();
        this.quantity = medicineQuantity.getQuantity();
    }
}
