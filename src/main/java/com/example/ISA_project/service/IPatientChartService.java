package com.example.ISA_project.service;

import com.example.ISA_project.model.dto.*;

import java.util.List;
import java.util.Set;

public interface IPatientChartService {
    Set<MedicineAllergyDTO> getPatientsAllergies(int id);
    Set<MedicineAllergyDTO> addPatientAllergy(MedicineAllergyDTO medicineAllergyDTO, int id);
    int findPatientChartId(int idPatient);
    List<FutureReservationDTO> removeReservation(int id, String serialNumber);
    List<FutureReservationDTO> getPatientsFutureReservations(int id);
    List<ReviewObjectDTO> getPatientDermatologist(int idPatient);
    List<ReviewObjectDTO> getPatientPharmacist(int idPatient);
    List<ReviewObjectDTO> getPatientMedicine(int idPatient);
    Set<ReviewObjectDTO> getPatientPharmacy(int idPatient);
    List<ExaminationDTO> getPatientPreviousExaminations(int id);
}
