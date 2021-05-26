package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.dto.MedicineAllergyDTO;
import com.example.ISA_project.repository.MedicineRepository;
import com.example.ISA_project.service.IMedicineService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineService implements IMedicineService {
    private final MedicineRepository medicineRepository;
    public MedicineService(MedicineRepository medicineRepository){
        this.medicineRepository = medicineRepository;
    }


    @Override
    public List<MedicineAllergyDTO> getDistinctMedicine() {
        List<MedicineAllergyDTO> allergies = new ArrayList<MedicineAllergyDTO>();
        for(String medicine : medicineRepository.getDistinctName()){
            allergies.add(new MedicineAllergyDTO(medicine));
        }
        return allergies;
    }
}
