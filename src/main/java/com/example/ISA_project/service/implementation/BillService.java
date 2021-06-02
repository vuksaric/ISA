package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Bill;
import com.example.ISA_project.model.Medicine;
import com.example.ISA_project.model.Pharmacy;
import com.example.ISA_project.model.PricelistMedicine;
import com.example.ISA_project.repository.BillRepository;
import com.example.ISA_project.service.IBillService;
import com.example.ISA_project.service.IPriceListMedicineService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BillService implements IBillService {
    private final BillRepository billRepository;
    private final IPriceListMedicineService priceListMedicineService;

    public BillService(BillRepository billRepository, IPriceListMedicineService priceListMedicineService){
        this.billRepository = billRepository;
        this.priceListMedicineService = priceListMedicineService;
    }
    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill newBill(Medicine medicine, Pharmacy pharmacy) {
        PricelistMedicine pricelistMedicine = priceListMedicineService.find(medicine.getId(),pharmacy.getId());
        float price = pricelistMedicine.getPrice();
        LocalDateTime date = LocalDateTime.now();
        return billRepository.save(new Bill(medicine,pharmacy,price,date));
    }
}
