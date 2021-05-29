package com.example.ISA_project.service;



import com.example.ISA_project.model.Period;
import com.example.ISA_project.model.dto.ExaminationDTO;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.model.dto.ReportRequest;

import java.util.List;

public interface IExaminationService {
    List<ExaminationDTO> findAllFree();
    List<MedicineDTO> getMedicines(int id);
    boolean prescribeMedicine(int idConsultation, int idMedicine);
    List<MedicineDTO> getReplacements(int idConsultation, int idMedicine);
    ExaminationDTO finish(ReportRequest request);
    List<Period> freePeriodsPatient(List<Period> periods, int id);
    List<ExaminationDTO> findAllFutureByPatient(int id);
    ExaminationDTO reserveExamination(int id, int idPatient);
    ExaminationDTO cancelExamination(int id);


}
