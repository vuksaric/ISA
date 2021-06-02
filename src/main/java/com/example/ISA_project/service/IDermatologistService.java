package com.example.ISA_project.service;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Examination;
import com.example.ISA_project.model.Period;
import com.example.ISA_project.model.dto.WorkDayDTO;

import java.time.LocalDate;
import java.util.List;

public interface IDermatologistService {

    List<WorkDayDTO> getWorkdays(int id);
    List<Period> freePeriods(int id, LocalDate date, int pharmacyId);
    void addNewExamination(Examination examination);
}
