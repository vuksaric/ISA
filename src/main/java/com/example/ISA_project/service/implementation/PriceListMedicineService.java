package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.PricelistMedicine;
import com.example.ISA_project.repository.PriceListMedicineRepository;
import com.example.ISA_project.service.IPriceListMedicineService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceListMedicineService implements IPriceListMedicineService {
    private final PriceListMedicineRepository priceListMedicineRepository;

    public PriceListMedicineService(PriceListMedicineRepository priceListMedicineRepository){
        this.priceListMedicineRepository = priceListMedicineRepository;
    }
    @Override
    public PricelistMedicine find(int medicineId, int pharmacyId)
    {
        return priceListMedicineRepository.find(medicineId, pharmacyId);
    }
}
