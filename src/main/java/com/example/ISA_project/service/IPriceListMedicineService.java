package com.example.ISA_project.service;

import com.example.ISA_project.model.PricelistMedicine;

import java.time.LocalDateTime;

public interface IPriceListMedicineService {
    PricelistMedicine find(int medicineId, int pharmacyId);
}
