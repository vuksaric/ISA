package com.example.ISA_project.service;

import com.example.ISA_project.model.OrderList;
import com.example.ISA_project.model.dto.AcceptOfferDTO;
import com.example.ISA_project.model.dto.MedicineOrderDTO;

import java.util.List;

public interface IOrderService {
    void newOrder(MedicineOrderDTO order);
    void deleteOrder(int id);
    List<MedicineOrderDTO> getAll(int id);
    void acceptOffer(AcceptOfferDTO acceptOfferDTO);
}
