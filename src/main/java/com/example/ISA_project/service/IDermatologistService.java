package com.example.ISA_project.service;

import com.example.ISA_project.model.Dermatologist;
import com.example.ISA_project.model.Vacation;
import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Examination;
import com.example.ISA_project.model.Period;
import com.example.ISA_project.model.dto.DermatologistDTO;
import com.example.ISA_project.model.dto.WorkDayDTO;

import java.time.LocalDate;
import java.util.List;

public interface IDermatologistService {

    List<WorkDayDTO> getWorkdays(int id);
    void addVacation(Vacation vacation);
    Dermatologist getById(int id);
    List<Period> freePeriods(int id, LocalDate date, int pharmacyId);
    void addNewExamination(Examination examination);
    List<DermatologistDTO> getAll();
    List<DermatologistDTO> search(String input);
    void save(Dermatologist dermatologist);
}
