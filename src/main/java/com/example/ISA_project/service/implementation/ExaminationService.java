package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Examination;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.dto.ExaminationDTO;
import com.example.ISA_project.repository.ExaminationRepository;
import com.example.ISA_project.service.IExaminationService;
import com.example.ISA_project.service.IPatientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ExaminationService implements IExaminationService {

    private  final ExaminationRepository examinationRepository;
    private final IPatientService patientService;

    public ExaminationService(ExaminationRepository examinationRepository, IPatientService patientService){
        this.examinationRepository=examinationRepository;
        this.patientService = patientService;
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
    public List<ExaminationDTO> findAllFutureByPatient(int id) {
        List<ExaminationDTO> examintaions = new ArrayList<ExaminationDTO>();
        for(Examination examination: examinationRepository.findAllFutureByPatient(id)){
            examintaions.add(new ExaminationDTO((examination)));
        }
        return examintaions;
    }

    @Override
    public ExaminationDTO reserveExamination(int id, int idPatient) {
        Examination examination = examinationRepository.findExaminationById(id);
        examination.setFree(false);
        Patient patient = patientService.findOneById(idPatient);
        examination.setPatient(patient);
        examinationRepository.save(examination);
        return new ExaminationDTO(examinationRepository.findExaminationById(id));
    }

    @Override
    public ExaminationDTO cancelExamination(int id) {
        Examination examination = examinationRepository.findExaminationById(id);
        examination.setFree(true);
        examination.setPatient(null);
        examinationRepository.save(examination);
        return new ExaminationDTO(examinationRepository.findExaminationById(id));
    }
}
