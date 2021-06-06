package com.example.ISA_project.service;

import com.example.ISA_project.model.MedicineNotification;
import com.example.ISA_project.model.dto.MedicineNotificationDTO;

import java.util.List;

public interface IMedicineNotificationService {

    MedicineNotification saveNotification(MedicineNotification medicineNotification);
    List<MedicineNotificationDTO> getAll(int pharmacyId);
}
