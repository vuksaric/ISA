package com.example.ISA_project.service;


import com.example.ISA_project.model.dto.ExaminationDTO;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.model.dto.ReportRequest;

import java.util.List;

public interface IExaminationService {
    List<ExaminationDTO> findAllFree();
    ExaminationDTO reserveExamination(int id);
    List<MedicineDTO> getMedicines(int id);
    boolean prescribeMedicine(int idConsultation, int idMedicine);
    List<MedicineDTO> getReplacements(int idConsultation, int idMedicine);
    ExaminationDTO finish(ReportRequest request);

}
