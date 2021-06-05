package com.example.ISA_project.service;

import com.example.ISA_project.model.PatientChart;
import com.example.ISA_project.model.Period;
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
    Set<ReviewObjectDTO> getPatientMedicine(int idPatient);
    Set<ReviewObjectDTO> getPatientPharmacy(int idPatient);
    List<ExaminationDTO> getPatientPreviousExaminations(int id);

    List<Period> freePeriods(List<Period> periods, int id);

    List<ConsultationDTO> getPatientPreviousConsultations(int id);
    List<ConsultationDTO> getPatientUpcomingConsultations(int id);

    List<ERecipeDTO> getPatientERecipes(int id);
    Set<MedicineDTO> getPatientERecipeMedicines(int id);
    PatientChart save(PatientChart patientChart);

}
