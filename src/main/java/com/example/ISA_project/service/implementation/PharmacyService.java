package com.example.ISA_project.service.implementation;


import com.example.ISA_project.model.*;
import com.example.ISA_project.model.Bill;
import com.example.ISA_project.model.MedicineQuantity;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.model.dto.PharmacistDTO;
import com.example.ISA_project.model.dto.PharmacyDTO;
import com.example.ISA_project.repository.PharmacyRepository;
import com.example.ISA_project.service.IBillService;
import com.example.ISA_project.service.IMedicineService;
import com.example.ISA_project.service.IPharmacyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PharmacyService implements IPharmacyService {
    private final PharmacyRepository pharmacyRepository;
    private final IMedicineService medicineService;
    private final IBillService billService;

    public PharmacyService(PharmacyRepository pharmacyRepository, IMedicineService medicineService, IBillService billService){
        this.pharmacyRepository=pharmacyRepository;
        this.medicineService = medicineService;
        this.billService = billService;
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
    public Set<PharmacyDTO> getPharmaciesWithFreeAppointment(LocalDateTime start) {
        WorkdayPharmacist workdayPharmacist = null;
        boolean consultationExistance = false;
        List<PharmacyDTO> pharmacyDTOS = new ArrayList<>();

        for(Pharmacy pharmacy : pharmacyRepository.findAll()){
            for(Pharmacist pharmacist : pharmacy.getPharmacists()){
                if(checkIfPharmacistWorks(pharmacist, start)){
                    workdayPharmacist = findWorkday(start, pharmacist.getWorkdays());
                    if (workdayPharmacist == null) {
                        break;
                    }
                    consultationExistance = checkCnocultationExistance(workdayPharmacist.getConsultations(), start);
                    if(!consultationExistance){
                        pharmacyDTOS.add(new PharmacyDTO(pharmacy));
                    }
                }
            }
        }
        return new HashSet<>(pharmacyDTOS);
    }

    @Override
    public List<PharmacistDTO> getAvailablePharmacistsByPharmacy(int id, LocalDateTime start) {
        Pharmacy pharmacy = pharmacyRepository.findOneById(id);
        List<PharmacistDTO> pharmacistDTOS = new ArrayList<>();
        WorkdayPharmacist workdayPharmacist = null;
        boolean consultationExistance = false;

            for(Pharmacist pharmacist : pharmacy.getPharmacists()){
                if(checkIfPharmacistWorks(pharmacist, start)){
                    workdayPharmacist = findWorkday(start, pharmacist.getWorkdays());
                    if (workdayPharmacist == null) {
                        break;
                    }
                    consultationExistance = checkCnocultationExistance(workdayPharmacist.getConsultations(), start);
                    if(!consultationExistance){
                        pharmacistDTOS.add(new PharmacistDTO(pharmacist.getFullName(),pharmacist.getMark(),pharmacist.getId()));
                    }
                }
            }
        return pharmacistDTOS;
    }


    private boolean checkCnocultationExistance(List<Consultation> consultations, LocalDateTime start){
        boolean existance = false;
        for(Consultation consultation : consultations){
            if(consultation.getPeriod().getStart_date().toLocalTime().equals(start.toLocalTime())){
                existance = true;
                break;
            }
        }
        return existance;
    }

    private WorkdayPharmacist findWorkday(LocalDateTime date, List<WorkdayPharmacist> workdayPharmacists){
        WorkdayPharmacist workday = null;
        for(WorkdayPharmacist workdayPharmacist : workdayPharmacists){
            if(workdayPharmacist.getDate().equals(date.toLocalDate())){
                workday = workdayPharmacist;
                break;
            }
        }
        return workday;
    }

    private boolean checkIfPharmacistWorks(Pharmacist pharmacist, LocalDateTime date){
        WorkingHours workingHours = pharmacist.getWorkingHours();

        /*dodati proveru da li je na godisnjem*/

        if(date.toLocalTime().isAfter(workingHours.getStartTime()) &&
                date.toLocalTime().plusMinutes(29).isBefore(workingHours.getEndTime()))
            return true;
        else
            return false;
    }

}
