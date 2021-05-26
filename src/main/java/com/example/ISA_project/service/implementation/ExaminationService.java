package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Examination;
import com.example.ISA_project.model.dto.ExaminationDTO;
import com.example.ISA_project.repository.ExaminationRepository;
import com.example.ISA_project.service.IExaminationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ExaminationService implements IExaminationService {

    private  final ExaminationRepository examinationRepository;
    public ExaminationService(ExaminationRepository examinationRepository){
        this.examinationRepository=examinationRepository;
    }
    @Override
    public List<ExaminationDTO> findAllFree() {
        List<ExaminationDTO> examintaions = new ArrayList<ExaminationDTO>();
        for(Examination examination: examinationRepository.findAllFree()){
          examintaions.add(new ExaminationDTO((examination)));
        }
        return examintaions;
    }

    @Override
    public ExaminationDTO reserveExamination(int id) {
        Examination examination = examinationRepository.findExaminationById(id);
        examination.setFree(false);
        examinationRepository.save(examination);
        return new ExaminationDTO(examinationRepository.findExaminationById(id));
    }
}
