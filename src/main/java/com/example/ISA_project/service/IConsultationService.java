package com.example.ISA_project.service;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Period;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.model.dto.PreviousConsultationDTO;
import com.example.ISA_project.model.dto.ReportRequest;

import java.time.LocalDate;
import java.util.List;

public interface IConsultationService {

    List<PreviousConsultationDTO> getPreviousByPharmacist(int id);
    List<MedicineDTO> getMedicines(int id);
    boolean prescribeMedicine(int idConsultation, int idMedicine);
    List<MedicineDTO> getReplacements(int idConsultation, int idMedicine);
    Consultation finish(ReportRequest request);
    List<Period> freePeriods(int id, LocalDate date);
}
