package com.example.ISA_project.service;

import com.example.ISA_project.model.Bill;
import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.Pharmacy;

public interface IBillService {
    Bill save(Bill bill);
    Bill newBill(Medicine medicine, Pharmacy pharmacy);
}
