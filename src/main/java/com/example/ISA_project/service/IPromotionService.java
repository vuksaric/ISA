package com.example.ISA_project.service;

import com.example.ISA_project.model.Action;
import com.example.ISA_project.model.dto.ActionDTO;

import java.util.List;

public interface IPromotionService {
    void newPromotion(ActionDTO action);
    List<Action> getAll();
}
