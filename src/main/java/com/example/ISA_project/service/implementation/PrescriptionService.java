package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Prescription;
import com.example.ISA_project.repository.PresriptionRepository;
import com.example.ISA_project.service.IPrescriptionService;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService implements IPrescriptionService {

    private final PresriptionRepository presriptionRepository;

    public PrescriptionService(PresriptionRepository presriptionRepository)
    {
        this.presriptionRepository = presriptionRepository;
    }


    @Override
    public Prescription writePresription(Prescription prescription) {
        return presriptionRepository.save(prescription);
    }
}
