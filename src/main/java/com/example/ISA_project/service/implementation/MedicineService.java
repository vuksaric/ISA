package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Medicine;

import com.example.ISA_project.model.dto.MedicineAllergyDTO;
import com.example.ISA_project.repository.MedicineRepository;

import com.example.ISA_project.model.dto.MedicineDTO;


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
        for (String medicine : medicineRepository.getDistinctName()) {
            allergies.add(new MedicineAllergyDTO(medicine));
        }
        return allergies;
    }



    @Override
    public MedicineDTO getMedicineById(int id) {
        MedicineDTO retVal = null;
        try{
            Medicine medicineResponse = medicineRepository.findOneById(id);
            retVal = new MedicineDTO(medicineResponse);
        }catch(Exception e){
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public List<MedicineDTO> getAllMedicine() {
        List<MedicineDTO> retVal = new ArrayList<>();
        try{
            List<Medicine> medicineResponse = medicineRepository.findAll();
            for(Medicine m : medicineResponse){
                retVal.add(new MedicineDTO(m));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void deleteMedicine(int id) {
        try{
            Medicine m = medicineRepository.findOneById(id);
            medicineRepository.delete(m);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Medicine findOneById(int id) {
       return medicineRepository.findOneById(id);
    }

    @Override
    public List<MedicineAllergyDTO> findMedicines() {
        List<MedicineAllergyDTO> allergies = new ArrayList<MedicineAllergyDTO>();
        for (String medicine : medicineRepository.findMedicines()) {
            allergies.add(new MedicineAllergyDTO(medicine));
        }
        return allergies;
    }


}
