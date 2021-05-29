package com.example.ISA_project.service;



import com.example.ISA_project.model.dto.ExaminationDTO;

import java.util.List;

public interface IExaminationService {
    List<ExaminationDTO> findAllFree();
    List<ExaminationDTO> findAllFutureByPatient(int id);
    ExaminationDTO reserveExamination(int id, int idPatient);
    ExaminationDTO cancelExamination(int id);

}
