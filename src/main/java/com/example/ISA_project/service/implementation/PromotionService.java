package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Action;
import com.example.ISA_project.repository.PromotionRepository;
import com.example.ISA_project.service.IPromotionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionService implements IPromotionService {
    private final PromotionRepository promotionRepository;


    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public void newPromotion(Action action) {
        try{
            promotionRepository.save(action);
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
