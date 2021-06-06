package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.MedicineNotification;
import com.example.ISA_project.model.dto.MedicineNotificationDTO;
import com.example.ISA_project.repository.MedicineNotificationRepository;
import com.example.ISA_project.service.IMedicineNotificationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<MedicineNotificationDTO> getAll(int pharmacyId) {
        List<MedicineNotificationDTO> medicineNotifications = new ArrayList<>();
        try{
            for(MedicineNotification m : medicineNotificationRepository.findAll()){
                if(m.getPharmacy_id() == pharmacyId){
                    medicineNotifications.add(new MedicineNotificationDTO(m.getMedicine()));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return medicineNotifications;
    }
}
