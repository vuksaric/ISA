package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.WorkdayPharmacist;
import com.example.ISA_project.model.dto.AppointmentDTO;
import com.example.ISA_project.model.dto.WorkDayDTO;
import com.example.ISA_project.repository.WorkdayPharmacistRepository;
import com.example.ISA_project.service.IWorkdayPharmacistService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkdayPharmacistService implements IWorkdayPharmacistService {

    private final WorkdayPharmacistRepository workdayPharmacistRepository;

    public WorkdayPharmacistService(WorkdayPharmacistRepository workdayPharmacistRepository)
    {
        this.workdayPharmacistRepository = workdayPharmacistRepository;
    }

    @Override
    public List<AppointmentDTO> getConsultations(int id) {
        WorkdayPharmacist workday = workdayPharmacistRepository.findOneById(id);
        List<AppointmentDTO> result = new ArrayList<>();

        for(Consultation consultation : workday.getConsultations())
        {
            result.add(new AppointmentDTO(consultation));
        }
        return result;
    }



}
