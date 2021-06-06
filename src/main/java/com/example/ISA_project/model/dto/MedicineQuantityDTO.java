package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.MedicineQuantity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineQuantityDTO {
    private int id;
    private String medicine;
    private String name;
    private int quantity;
    private String manufacturer;
    private String issuingMode;
    private String shape;
    private String notes;

    public MedicineQuantityDTO(MedicineQuantity medicineQuantity){
        this.id = medicineQuantity.getId();
        this.medicine = medicineQuantity.getMedicine().getMedicineInformation();
        this.name = medicineQuantity.getMedicine().getName();
        this.quantity = medicineQuantity.getQuantity();
        this.manufacturer = medicineQuantity.getMedicine().getManufacturer();
        this.issuingMode = medicineQuantity.getMedicine().getIssuingMode().toString();
        this.shape = medicineQuantity.getMedicine().getShape();
        this.notes = medicineQuantity.getMedicine().getNotes();
    }
}
