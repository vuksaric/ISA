package com.example.ISA_project.service;

import com.example.ISA_project.model.dto.MedicineAllergyDTO;

import java.util.List;
import java.util.Set;

public interface IPatientChartService {
    Set<MedicineAllergyDTO> getPatientsAllergies(int id);
    Set<MedicineAllergyDTO> addPatientAllergy(MedicineAllergyDTO medicineAllergyDTO, int id);
}
