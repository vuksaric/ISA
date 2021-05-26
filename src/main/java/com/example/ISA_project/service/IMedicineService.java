package com.example.ISA_project.service;


import com.example.ISA_project.model.dto.MedicineAllergyDTO;

import java.util.List;

public interface IMedicineService {
    List<MedicineAllergyDTO> getDistinctMedicine();
}
