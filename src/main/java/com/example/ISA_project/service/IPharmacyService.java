package com.example.ISA_project.service;


import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.MedicineQuantity;
import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.dto.ConsultationDTO;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.model.dto.PharmacistDTO;
import com.example.ISA_project.model.dto.PharmacyDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface IPharmacyService {
    List<PharmacyDTO> findAll();
    List<PharmacyDTO> findAllDermatologist(int id);
    Boolean registerPharmacy(Pharmacy pharmacy);
    boolean prescribeMedicine(int idPharmacy, int idMedicine);
    List<MedicineDTO> getReplacements(int idPharmacy, int idMedicine);
    boolean checkQuantity(int idPharmacy, int idMedicine);
    Pharmacy findOneById(int id);
    List<PharmacyDTO> findPharmacyByMedicineQuantity(int id);
    Pharmacy subtractMedicineQuantity(int idMedicine, int idPharmacy);
    Pharmacy addMedicineQuantity(int idMedicine, int idPharmacy);
    List<PharmacyDTO> subscribedPharmacies(int idPatient);
    Pharmacy getByName(String name);
    Set<PharmacyDTO> getPharmaciesWithFreeAppointment(LocalDateTime start);
    List<PharmacistDTO> getAvailablePharmacistsByPharmacy(int id, LocalDateTime start);
}
