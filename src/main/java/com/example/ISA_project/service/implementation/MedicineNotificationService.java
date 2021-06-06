package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.MedicineNotification;
import com.example.ISA_project.repository.MedicineNotificationRepository;
import com.example.ISA_project.service.IMedicineNotificationService;
import org.springframework.stereotype.Service;

@Service
public class MedicineNotificationService implements IMedicineNotificationService {

    private final MedicineNotificationRepository medicineNotificationRepository;

    public MedicineNotificationService(MedicineNotificationRepository medicineNotificationRepository)
    {
        this.medicineNotificationRepository = medicineNotificationRepository;
    }


    @Override
    public MedicineNotification saveNotification(MedicineNotification medicineNotification) {
        return medicineNotificationRepository.save(medicineNotification);
    }
}
