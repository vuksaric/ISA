package com.example.ISA_project.service;

import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.dto.MedicineDTO;

import java.util.List;

public interface IMedicineService {
    MedicineDTO getMedicineById(int id);
    List<MedicineDTO> getAllMedicine();
    void deleteMedicine(int id);

}
