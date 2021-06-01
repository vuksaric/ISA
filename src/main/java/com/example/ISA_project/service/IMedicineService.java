package com.example.ISA_project.service;

import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.dto.MedicineAllergyDTO;
import com.example.ISA_project.model.dto.MedicineDTO;


import java.util.List;

public interface IMedicineService {

    List<MedicineAllergyDTO> getDistinctMedicine();

    MedicineDTO getMedicineById(int id);
    List<MedicineDTO> getAllMedicine();
    void deleteMedicine(int id);
    Boolean addMedicine(Medicine medicine);
    List<Medicine> getByType(String type);


}
