package com.example.ISA_project.service;


import com.example.ISA_project.model.dto.ExaminationDTO;

import java.util.List;

public interface IExaminationService {
    List<ExaminationDTO> findAllFree();
    ExaminationDTO reserveExamination(int id);

}
