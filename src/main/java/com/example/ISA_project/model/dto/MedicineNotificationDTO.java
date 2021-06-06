package com.example.ISA_project.model.dto;

import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.MedicineNotification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineNotificationDTO {
    String medicineName;
    String medicineType;
    String medicineManufacturer;
    String medicineShape;

    public MedicineNotificationDTO(Medicine m){
        this.medicineName = m.getName();
        this.medicineType = m.getType();
        this.medicineManufacturer = m.getManufacturer();
        this.medicineShape = m.getShape();
    }
}
