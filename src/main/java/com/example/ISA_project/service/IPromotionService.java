package com.example.ISA_project.service;

import com.example.ISA_project.model.Action;

import java.util.List;

public interface IPromotionService {
    void newPromotion(Action action);
    List<Action> getAll();
}
