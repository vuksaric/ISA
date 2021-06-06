package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Action;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.dto.ActionDTO;
import com.example.ISA_project.repository.PromotionRepository;
import com.example.ISA_project.service.IPromotionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionService implements IPromotionService {
    private final PromotionRepository promotionRepository;
    private final PharmacyService pharmacyService;
    private final EmailService emailService;


    public PromotionService(PromotionRepository promotionRepository, PharmacyService pharmacyService, EmailService emailService) {
        this.promotionRepository = promotionRepository;
        this.pharmacyService = pharmacyService;
        this.emailService = emailService;
    }

    @Override
    public void newPromotion(ActionDTO action) {
        try{
            promotionRepository.save(new Action(action.getStartDate(),action.getEndDate(),action.getText()));
            List<Patient> patients = pharmacyService.findOneById(action.getPharmacyId()).getSubscribers();
            for(Patient p : patients){
                emailService.sendPromotions(p,action);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Action> getAll() {
        List<Action> actions = new ArrayList<>();
        try{
            actions = promotionRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return actions;
    }
}
