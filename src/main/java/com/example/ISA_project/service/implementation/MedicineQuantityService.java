package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.MedicineQuantity;
import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.repository.MedicineQuantityRepository;
import com.example.ISA_project.service.IMedicineQuantityService;
import com.example.ISA_project.service.IMedicineService;
import com.example.ISA_project.service.IPharmacyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void addMedicineQuantity(int medicineId,int pharmacyId, int quantity) {
        try{
            Pharmacy pharmacy = pharmacyService.findOneById(pharmacyId);
            //List<MedicineQuantity> list = medicineQuantityRepository.findAll();
            boolean hasMedicine = false;
            for(MedicineQuantity mq : pharmacy.getMedicines()){
                if(medicineId == mq.getMedicine().getId()){
                    mq.setQuantity(mq.getQuantity() + quantity);
                    pharmacyService.save(pharmacy);
                    hasMedicine = true;
                    break;
                }
            }
            if(!hasMedicine){
                pharmacyService.newMedicineQuantity(medicineId,pharmacyId);
                //medicineQuantityRepository.save(new MedicineQuantity(medicineService.findOneById(medicineId),quantity));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public MedicineQuantity findOneById(int id){
        return medicineQuantityRepository.findById(id);
    }
}
