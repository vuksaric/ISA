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
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Integer> billReport(int id) {
        int jan = 0,feb = 0,mar = 0,apr = 0,may = 0,jun = 0,jul = 0,aug = 0,sep = 0,oct = 0,nov = 0,dec = 0;
        List<Integer> months = new ArrayList<>();
        try{
            List<Bill> bills = billRepository.findAll();
            for(Bill b : bills){
                if(b.getPharmacy().getId() == id && b.getDate().getYear() == 2021){
                    if(b.getDate().getMonthValue() == 1)
                        jan += 1;
                    if(b.getDate().getMonthValue() == 2)
                        feb += 1;
                    if(b.getDate().getMonthValue() == 3)
                        mar += 1;
                    if(b.getDate().getMonthValue() == 4)
                        apr += 1;
                    if(b.getDate().getMonthValue() == 5)
                        may += 1;
                    if(b.getDate().getMonthValue() == 6)
                        jun += 1;
                    if(b.getDate().getMonthValue() == 7)
                        jul += 1;
                    if(b.getDate().getMonthValue() == 8)
                        aug += 1;
                    if(b.getDate().getMonthValue() == 9)
                        sep += 1;
                    if(b.getDate().getMonthValue() == 10)
                        oct += 1;
                    if(b.getDate().getMonthValue() == 11)
                        nov += 1;
                    if(b.getDate().getMonthValue() == 12)
                        dec += 1;
                }
            }
            months.add(jan);
            months.add(feb);
            months.add(mar);
            months.add(apr);
            months.add(may);
            months.add(jun);
            months.add(jul);
            months.add(aug);
            months.add(sep);
            months.add(oct);
            months.add(nov);
            months.add(dec);
        }catch(Exception e){
            e.printStackTrace();
        }
        return months;
    }

    @Override
    public List<Integer> incomeReport(int id) {
        int jan = 0,feb = 0,mar = 0,apr = 0,may = 0,jun = 0,jul = 0,aug = 0,sep = 0,oct = 0,nov = 0,dec = 0;
        List<Integer> months = new ArrayList<>();
        try{
            List<Bill> bills = billRepository.findAll();
            for(Bill b : bills){
                if(b.getPharmacy().getId() == id && b.getDate().getYear() == 2021){
                    if(b.getDate().getMonthValue() == 1)
                        jan += b.getPrice();
                    if(b.getDate().getMonthValue() == 2)
                        feb += b.getPrice();
                    if(b.getDate().getMonthValue() == 3)
                        mar += b.getPrice();
                    if(b.getDate().getMonthValue() == 4)
                        apr += b.getPrice();
                    if(b.getDate().getMonthValue() == 5)
                        may += b.getPrice();
                    if(b.getDate().getMonthValue() == 6)
                        jun += b.getPrice();
                    if(b.getDate().getMonthValue() == 7)
                        jul += b.getPrice();
                    if(b.getDate().getMonthValue() == 8)
                        aug += b.getPrice();
                    if(b.getDate().getMonthValue() == 9)
                        sep += b.getPrice();
                    if(b.getDate().getMonthValue() == 10)
                        oct += b.getPrice();
                    if(b.getDate().getMonthValue() == 11)
                        nov += b.getPrice();
                    if(b.getDate().getMonthValue() == 12)
                        dec += b.getPrice();
                }
            }
            months.add(jan);
            months.add(feb);
            months.add(mar);
            months.add(apr);
            months.add(may);
            months.add(jun);
            months.add(jul);
            months.add(aug);
            months.add(sep);
            months.add(oct);
            months.add(nov);
            months.add(dec);
        }catch(Exception e){
            e.printStackTrace();
        }
        return months;
    }

}
