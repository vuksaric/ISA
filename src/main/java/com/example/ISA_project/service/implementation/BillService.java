package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Bill;
import com.example.ISA_project.repository.BillRepository;
import com.example.ISA_project.service.IBillService;
import org.springframework.stereotype.Service;

@Service
public class BillService implements IBillService {
    private final BillRepository billRepository;
    public BillService(BillRepository billRepository){
        this.billRepository = billRepository;
    }
    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }
}
