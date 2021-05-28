package com.example.ISA_project.service;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.dto.MedicineDTO;
import com.example.ISA_project.model.dto.PreviousConsultationDTO;
import com.example.ISA_project.model.dto.ReportRequest;

import java.util.List;

public interface IConsultationService {

    List<PreviousConsultationDTO> getPreviousByPharmacist(int id);
    List<MedicineDTO> getMedicines(int id);
    boolean PrescribeMedicine(int idConsultation, int idMedicine);
    List<MedicineDTO> getReplacements(int idConsultation, int idMedicine);
    Consultation finish(ReportRequest request);
}
