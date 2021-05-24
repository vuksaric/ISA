package com.example.ISA_project.service;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.dto.PreviousConsultationDTO;

import java.util.List;

public interface IConsultationService {

    List<PreviousConsultationDTO> getPreviousByPharmacist(int id);
}
