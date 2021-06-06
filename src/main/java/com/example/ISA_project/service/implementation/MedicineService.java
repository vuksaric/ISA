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
    public List<MedicineDTO> getAllByName(String name) {
        List<Medicine> medicines = medicineRepository.search(name);
        List<MedicineDTO> result = new ArrayList<>();
        for(Medicine medicine : medicines)
        {
            result.add(new MedicineDTO(medicine));

        }

        return result;
    }

    @Override
    public List<Integer> getReplacementIds(int id) {
        Medicine medicine = medicineRepository.findOneById(id);
        return medicine.getReplacements();
    }


    public Medicine findOneById(int id) {
       return medicineRepository.findOneById(id);
    }

    @Override
    public List<Medicine> findMedicines() {
        return medicineRepository.findMedicines();
    }

    @Override
    public List<Medicine> findMedicinesByName(String name) {
        return medicineRepository.getAllByName(name);
    }

    @Override
    public Boolean addMedicine(Medicine medicine){
        try{
            Medicine m = medicineRepository.save(medicine);
            if (m!=null)
                return true;
            else return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Medicine> getByType(String type){
        System.out.println(type);
        List<Medicine> retVal = new ArrayList<>();
        try{
            retVal.addAll(medicineRepository.getAllByType(type));
        }catch(Exception e){
            e.printStackTrace();
        }
        return retVal;
    }
}
