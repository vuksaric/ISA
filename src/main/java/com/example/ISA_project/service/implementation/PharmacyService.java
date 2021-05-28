package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.MedicineQuantity;
import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.model.dto.PharmacyDTO;
import com.example.ISA_project.repository.PharmacyRepository;
import com.example.ISA_project.service.IMedicineService;
import com.example.ISA_project.service.IPharmacyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacyService implements IPharmacyService {
    private final PharmacyRepository pharmacyRepository;
    private final IMedicineService medicineService;

    public PharmacyService(PharmacyRepository pharmacyRepository, IMedicineService medicineService){
        this.pharmacyRepository=pharmacyRepository;
        this.medicineService = medicineService;
    }
    @Override
    public List<PharmacyDTO> findAll() {
        List<PharmacyDTO> pharmacies = new ArrayList<PharmacyDTO>();
        for (Pharmacy pharmacy: pharmacyRepository.findAll()) {
            pharmacies.add(new PharmacyDTO(pharmacy.getName(), pharmacy.getMark(),
                    pharmacy.getAddress().getStreet(), pharmacy.getAddress().getTown()));
        }
        return pharmacies;
    }

    public Boolean registerPharmacy(Pharmacy pharmacy){
        Pharmacy ph = pharmacyRepository.save(pharmacy);
        if (ph != null)
            return true;
        else
            return false;
    }

    @Override
    public boolean prescribeMedicine(int idPharmacy, int idMedicine) {
        Pharmacy pharmacy = pharmacyRepository.findOneById(idPharmacy);
        for(MedicineQuantity medicine : pharmacy.getMedicines())
        {
            if(medicine.getMedicine().getId() == idMedicine)
            {
                medicine.setQuantity(medicine.getQuantity()-1);
                pharmacyRepository.save(pharmacy);
                return true;
            }
        }
        return false;
    }



    @Override
    public List<MedicineDTO> getReplacements(int idPharmacy, int idMedicine) {

        Pharmacy pharmacy = pharmacyRepository.findOneById(idPharmacy);
        List<Integer> replacements = medicineService.getReplacementIds(idMedicine);
        List<MedicineDTO> result = new ArrayList<>();

        for(Integer replacement : replacements)
        {
            for(MedicineQuantity medicineQuantity : pharmacy.getMedicines())
            {
                if(medicineQuantity.getMedicine().getId() == replacement && medicineQuantity.getQuantity()>0)
                    result.add(new MedicineDTO(medicineQuantity.getMedicine()));
            }
        }
        return result;
    }

    @Override
    public boolean checkQuantity(int idPharmacy, int idMedicine) {
        Pharmacy pharmacy = pharmacyRepository.findOneById(idPharmacy);
        for(MedicineQuantity medicine : pharmacy.getMedicines())
        {
            if(medicine.getMedicine().getId() == idMedicine)
            {
                if(medicine.getQuantity() > 0)
                {
                    return true;
                }
            }
        }
        return false;
    }


}
