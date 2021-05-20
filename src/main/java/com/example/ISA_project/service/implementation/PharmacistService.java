package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.AppointmentDTO;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.model.dto.WorkDayDTO;
import com.example.ISA_project.repository.ConsultationRepository;
import com.example.ISA_project.repository.PharmacistRepository;
import com.example.ISA_project.service.IPharmacistService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacistService implements IPharmacistService {

    private final PharmacistRepository pharmacistRepository;
    private final ConsultationRepository consultationRepository;


    public PharmacistService(PharmacistRepository pharmacistRepository, ConsultationRepository consultationRepository)
    {
        this.pharmacistRepository = pharmacistRepository;
        this.consultationRepository = consultationRepository;
    }

    public ProfileDTO getProfile(int id)
    {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        ProfileDTO profile = new ProfileDTO(pharmacist.getUser().getUsername(),pharmacist.getUser().getName(), pharmacist.getUser().getSurname(),
                pharmacist.getUser().getEmail(), pharmacist.getUser().getAddress(), pharmacist.getUser().getState(),
                pharmacist.getUser().getTown(), pharmacist.getUser().getPhone());
        return profile;
    }

    public List<WorkDayDTO> getWorkdays(int id)
    {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        List<WorkDayDTO> result = new ArrayList<>();
        for(WorkdayPharmacist workday : pharmacist.getWorkdays())
        {
            List<AppointmentDTO> consultationDTOS = new ArrayList<>();
            for(Consultation consultation : workday.getConsultations())
                consultationDTOS.add(new AppointmentDTO(consultation.getPeriod().getStart_date(),consultation.getPeriod().getEnd_date(),consultation.getPharmacy().getName()));
            result.add(new WorkDayDTO(workday.getPeriod().getStart_date(),consultationDTOS));
        }
        return result;
    }

}
