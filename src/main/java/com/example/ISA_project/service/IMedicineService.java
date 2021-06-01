package com.example.ISA_project.service;

import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.dto.MedicineAllergyDTO;
import com.example.ISA_project.model.dto.MedicineDTO;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface IMedicineService {

    List<MedicineAllergyDTO> getDistinctMedicine();

    MedicineDTO getMedicineById(int id);
    List<MedicineDTO> getAllMedicine();
    void deleteMedicine(int id);
    List<MedicineDTO> getAllByName(String name);
    List<Integer> getReplacementIds(int id);
    Medicine findOneById(int id);
    List<MedicineAllergyDTO> findMedicines();

    List<Medicine> findMedicinesByName(String  name);

}
