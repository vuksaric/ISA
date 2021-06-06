package com.example.ISA_project.service;


import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.MedicineQuantity;
import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.dto.ConsultationDTO;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.model.dto.PharmacistDTO;
import com.example.ISA_project.model.dto.PharmacyDTO;
import com.example.ISA_project.model.dto.*;
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
    PharmacyDTO findOneByIdDTO(int id);
    List<PharmacyDTO> findPharmacyByMedicineQuantity(int id);
    Pharmacy subtractMedicineQuantity(int idMedicine, int idPharmacy);
    Pharmacy addMedicineQuantity(int idMedicine, int idPharmacy);
    List<PharmacyDTO> subscribedPharmacies(int idPatient);
    Pharmacy getByName(String name);
    Set<PharmacyDTO> getPharmaciesWithFreeAppointment(LocalDateTime start);
    List<PharmacistDTO> getAvailablePharmacistsByPharmacy(int id, LocalDateTime start);
    List<PharmacistDTO> getAllPharmacists(int id);
    List<DermatologistDTO> getAllDermatologists(int id);
    List<MedicineQuantityDTO> getAllMedicines(int id);
    float getMark(int id);
    void newMedicineQuantity(int medicineId, int pharmacyId);
    List<MedicineQuantityDTO> removeMedicineQuantity(int medicineId, int pharmacyId);
    List<MedicineQuantityDTO> search(String input, int pharmacyId);
    List<MedicineQuantityDTO> getAllMedicinesInPharmacy(int id);
    List<PharmacistDTO> addPharmacistInPharmacy(RegistrationDTO pharmacist, int pharmacyId);
    List<PharmacistDTO> removePharmacistInPharmacy(int pharmacistId, int pharmacyId);
    List<PharmacistDTO> searchPharmacistInPharmacy(String input, int pharmacyId);
    List<DermatologistDTO> addDermatologistInPharmacy(int dermatologistId, int pharmacyId, WorkingHoursDTO workingHoursDTO);
    List<DermatologistDTO> removeDermatologistInPharmacy(int dermatologistId, int pharmacyId);
    List<DermatologistDTO> searchDermatologistInPharmacy(String input, int pharmacyId);
    List<DermatologistDTO> getDermatologistDifference(int pharmacyId);
    List<WorkingHoursResponseDTO> getDermatologistShift(DermatologistFreePeriodsRequestDTO dermatologistFreePeriodsRequestDTO);
    void makeReservation(NewExaminationDTO newExaminationDTO);
    void save(Pharmacy pharmacy);

}
