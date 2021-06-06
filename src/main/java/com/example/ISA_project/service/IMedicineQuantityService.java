package com.example.ISA_project.service;

import com.example.ISA_project.model.MedicineQuantity;
import com.example.ISA_project.model.dto.MedicineDTO;

import java.util.List;

public interface IMedicineQuantityService {
    List<MedicineDTO> getDifference(int id);
    void addMedicineQuantity(int medicineId, int pharmacyId, int quantity);
}
