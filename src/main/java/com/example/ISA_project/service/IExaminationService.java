package com.example.ISA_project.service;



import com.example.ISA_project.model.Period;
import com.example.ISA_project.model.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface IExaminationService {
    List<ExaminationDTO> findAllFree();
    List<MedicineDTO> getMedicines(int id);
    boolean prescribeMedicine(int idConsultation, int idMedicine);
    List<MedicineDTO> getReplacements(int idConsultation, int idMedicine);
    ExaminationDTO finish(ReportRequest request);
    List<ExaminationDTO> findAllFutureByPatient(int id);
    ExaminationDTO reserveExamination(int id, int idPatient);
    ExaminationDTO cancelExamination(int id);
    List<AppointmentDTO> findFutureByPatient(int id);
    List<PreviousAppointmentDTO> getAllPreviousByDermatologist(int id);
    List<Period> freePeriods(int id, LocalDate date);
    AppointmentDTO newExaminationDermatologist(AppointmentRequest request);
    void addPenaltyPoint(int id);

}
