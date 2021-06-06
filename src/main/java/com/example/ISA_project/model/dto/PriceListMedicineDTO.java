package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.Period;
import com.example.ISA_project.model.PricelistMedicine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceListMedicineDTO {
    int id;
    int medicineId;
    String medicineName;
    int pharmacyId;
    float price;
    String start;
    String end;
    String duration;

    public PriceListMedicineDTO(PricelistMedicine p){
        this.id = p.getId();
        this.medicineId = p.getMedicine().getId();
        this.pharmacyId = p.getPharmacy().getId();
        this.medicineName = p.getMedicine().getName();
        this.price = p.getPrice();
        this.start = p.getValidity().getStart_date().toString();
        this.end = p.getValidity().getEnd_date().toString();
    }

}
