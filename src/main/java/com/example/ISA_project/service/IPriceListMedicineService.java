package com.example.ISA_project.service;

import com.example.ISA_project.model.PricelistMedicine;
import com.example.ISA_project.model.dto.PriceListMedicineDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IPriceListMedicineService {
    PricelistMedicine find(int medicineId, int pharmacyId);
    List<PriceListMedicineDTO> getAll(int pharmacyId);
    void edit(PriceListMedicineDTO priceListMedicineDTO);

}
