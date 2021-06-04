package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.MedicineQuantity;
import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.repository.MedicineQuantityRepository;
import com.example.ISA_project.repository.MedicineRepository;
import com.example.ISA_project.repository.PharmacyRepository;
import com.example.ISA_project.service.IMedicineQuantityService;
import com.example.ISA_project.service.IMedicineService;
import com.example.ISA_project.service.IPharmacyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineQuantityService implements IMedicineQuantityService {
    private final MedicineQuantityRepository medicineQuantityRepository;
    private final IMedicineService medicineService;
    private final IPharmacyService pharmacyService;

    public MedicineQuantityService(MedicineQuantityRepository medicineQuantityRepository, IMedicineService medicineService, IPharmacyService pharmacyService) {
        this.medicineQuantityRepository = medicineQuantityRepository;
        this.medicineService = medicineService;
        this.pharmacyService = pharmacyService;
    }

    public List<MedicineDTO> getDifference(int id){
        List<MedicineDTO> medicines = new ArrayList<>();
        try{
            Pharmacy p = pharmacyService.findOneById(id);
            for(MedicineDTO m : medicineService.getAllMedicine()){
                boolean isSame = false;
                for(MedicineQuantity mq : p.getMedicines()) {
                        if (m.getName().equals(mq.getMedicine().getName()) && m.getManufacturer().equals(mq.getMedicine().getManufacturer())) {
                            isSame = true;
                        }
                }
                if(!isSame){
                    medicines.add(m);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return medicines;
    }
}
