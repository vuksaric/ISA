package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Pharmacist;
import com.example.ISA_project.model.WorkdayPharmacist;
import com.example.ISA_project.model.dto.ConsultationDTO;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.model.dto.WorkDayPharmacistDTO;
import com.example.ISA_project.repository.PharmacistRepository;
import com.example.ISA_project.service.IPharmacistService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PharmacistService implements IPharmacistService {

    private final PharmacistRepository pharmacistRepository;

    public PharmacistService(PharmacistRepository pharmacistRepository)
    {
        this.pharmacistRepository = pharmacistRepository;
    }

    public ProfileDTO getProfile(int id)
    {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        ProfileDTO profile = new ProfileDTO(pharmacist.getUser().getUsername(),pharmacist.getUser().getName(), pharmacist.getUser().getSurname(),
                pharmacist.getUser().getEmail(), pharmacist.getUser().getAddress(), pharmacist.getUser().getState(),
                pharmacist.getUser().getTown(), pharmacist.getUser().getPhone());
        return profile;
    }

    public List<WorkDayPharmacistDTO> getWorkdays(int id)
    {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        List<WorkDayPharmacistDTO> result = new ArrayList<>();
        for(WorkdayPharmacist workday : pharmacist.getWorkdays())
        {
            List<ConsultationDTO> consultationDTOS = new ArrayList<>();
            for(Consultation consultation : workday.getConsultations())
                consultationDTOS.add(new ConsultationDTO(consultation.getPeriod().getStart_date(),consultation.getPeriod().getEnd_date(),consultation.getPharmacy().getName()));
            result.add(new WorkDayPharmacistDTO(workday.getPeriod().getStart_date(),consultationDTOS));
        }
        return result;
    }

    public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }
}
