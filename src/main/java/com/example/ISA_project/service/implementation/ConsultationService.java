package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.Pharmacist;
import com.example.ISA_project.model.dto.PreviousConsultationDTO;
import com.example.ISA_project.repository.ConsultationRepository;
import com.example.ISA_project.repository.PharmacistRepository;
import com.example.ISA_project.service.IConsultationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultationService implements IConsultationService {

    private final ConsultationRepository consultationRepository;

    public ConsultationService(ConsultationRepository consultationRepository)
    {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public List<PreviousConsultationDTO> getPreviousByPharmacist(int id) {

        List<Consultation> consultations= consultationRepository.findAll();
        List<PreviousConsultationDTO> result = new ArrayList<>();
        LocalDateTime today = LocalDateTime.now();
        for(Consultation consultation : consultations) {
            if (consultation.getPharmacist().getId() == id && consultation.getPeriod().getEnd_date().isBefore(today))
                result.add(new PreviousConsultationDTO(consultation.getPatient().getUser().getId(),consultation.getPatient().getUser().getName(), consultation.getPatient().getUser().getSurname(),
                        consultation.getPatient().getUser().getAddress().getFullAdress(), consultation.getPeriod().getStart_date(), consultation.getPharmacy().getName()));
        }
        return result;
    }



}

