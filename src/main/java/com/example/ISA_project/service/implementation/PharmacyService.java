package com.example.ISA_project.service.implementation;


import com.example.ISA_project.model.*;
import com.example.ISA_project.model.Bill;
import com.example.ISA_project.model.MedicineQuantity;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.dto.CheckSubscriptionDTO;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.model.dto.PharmacyDTO;
import com.example.ISA_project.repository.PharmacyRepository;
import com.example.ISA_project.service.IBillService;
import com.example.ISA_project.service.IMedicineService;
import com.example.ISA_project.service.IPatientService;
import com.example.ISA_project.service.IPharmacyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacyService implements IPharmacyService {
    private final PharmacyRepository pharmacyRepository;
    private final IMedicineService medicineService;
    private final IBillService billService;
    private final IPatientService patientService;

    public PharmacyService(PharmacyRepository pharmacyRepository, IMedicineService medicineService, IBillService billService,IPatientService patientService){
        this.pharmacyRepository=pharmacyRepository;
        this.medicineService = medicineService;
        this.billService = billService;
        this.patientService = patientService;
    }
    @Override
    public List<PharmacyDTO> findAll() {
        List<PharmacyDTO> pharmacies = new ArrayList<PharmacyDTO>();
        for (Pharmacy pharmacy: pharmacyRepository.findAll()) {
            pharmacies.add(new PharmacyDTO(pharmacy));
        }
        return pharmacies;
    }

    @Override
    public List<PharmacyDTO> findAllDermatologist(int id) {
        List<PharmacyDTO> pharmacies = new ArrayList<PharmacyDTO>();
        for (Pharmacy pharmacy: pharmacyRepository.findAll()) {
            for(Dermatologist dermatologist : pharmacy.getDermatologist())
            {
                if(dermatologist.getId() == id) {
                    pharmacies.add(new PharmacyDTO(pharmacy));
                    break;
                }
            }
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
        for (MedicineQuantity medicine : pharmacy.getMedicines()) {
            if (medicine.getMedicine().getId() == idMedicine) {
                if (medicine.getQuantity() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public Pharmacy findOneById(int id) {
        return pharmacyRepository.findOneById(id);
    }

    @Override
    public List<PharmacyDTO> findPharmacyByMedicineQuantity(int id) {
        List<PharmacyDTO> pharmacies = new ArrayList<PharmacyDTO>();
        for(Pharmacy pharmacy : pharmacyRepository.findAll()){
            for(MedicineQuantity medicineQuantity : pharmacy.getMedicines()){
                if(medicineQuantity.getMedicine().getId()==id && medicineQuantity.getQuantity()>0){
                    pharmacies.add(new PharmacyDTO(pharmacy));
                }
            }
        }
        return pharmacies;
    }
    @Override
    public Pharmacy subtractMedicineQuantity(int idMedicine, int idPharmacy){
        Pharmacy pharmacy = findOneById(idPharmacy);
        for(MedicineQuantity medicineQuantity : pharmacy.getMedicines()){
            if(medicineQuantity.getMedicine().getId()==idMedicine){
                medicineQuantity.setQuantity(medicineQuantity.getQuantity()-1);
                Bill bill = billService.newBill(medicineQuantity.getMedicine(), pharmacy);
            }
        }
        pharmacyRepository.save(pharmacy);

        return pharmacy;
    }

    @Override
    public Pharmacy addMedicineQuantity(int idMedicine, int idPharmacy) {
        Pharmacy pharmacy = findOneById(idPharmacy);
        for(MedicineQuantity medicineQuantity : pharmacy.getMedicines()){
            if(medicineQuantity.getMedicine().getId()==idMedicine){
                medicineQuantity.setQuantity(medicineQuantity.getQuantity()+1);
            }
        }
        pharmacyRepository.save(pharmacy);
        return pharmacy;
    }

    @Override
    public List<PharmacyDTO> subscribedPharmacies(int idPatient) {
        List<PharmacyDTO> pharmacies = new ArrayList<>();
        for(Pharmacy pharmacy : pharmacyRepository.findAll()){
            for(Patient patient : pharmacy.getSubscribers()){
                if(patient.getId()==idPatient){
                    pharmacies.add(new PharmacyDTO(pharmacy));
                }
            }
        }
        return pharmacies;
    }

    @Override
    public Pharmacy getByName(String name) {
        return pharmacyRepository.findOneByName(name);
    }

    @Override
    public Boolean checkSubscription(CheckSubscriptionDTO ids) {
        Pharmacy pharmacy = pharmacyRepository.findOneById(ids.getPharmacy_id());
        Patient patient = patientService.getByUserId(ids.getUser_id());
        for(Patient p : pharmacy.getSubscribers()){
            if(p.getId() == ids.getUser_id()){
                return true;
            }
        }
        List<Patient> plist = new ArrayList<>();
        plist.addAll(pharmacy.getSubscribers());
        plist.add(patient);
        pharmacy.setSubscribers(plist);
        pharmacyRepository.save(pharmacy);
        return false;
    }

    @Override
    public Boolean deleteSubscription(CheckSubscriptionDTO ids) {
        Pharmacy pharmacy = pharmacyRepository.findOneById(ids.getPharmacy_id());
        List<Patient> plist = new ArrayList<>();
        Boolean check = false;
        for(Patient p : pharmacy.getSubscribers()) {
            if (p.getId() != ids.getUser_id()) {
                plist.add(p);
            }else
                check = true;
        }
        pharmacy.setSubscribers(plist);
        pharmacyRepository.save(pharmacy);
        return check;
    }


}
