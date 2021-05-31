package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.AppointmentDTO;
import com.example.ISA_project.model.dto.WorkDayDTO;
import com.example.ISA_project.repository.DermatologistRepository;
import com.example.ISA_project.service.IDermatologistService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DermatologistService implements IDermatologistService {

    private final DermatologistRepository dermatologistRepository;

    public DermatologistService(DermatologistRepository dermatologistRepository)
    {
        this.dermatologistRepository = dermatologistRepository;
    }

    public List<WorkDayDTO> getWorkdays(int id)
    {
        Dermatologist dermatologist = dermatologistRepository.findOneById(id);
        List<WorkDayDTO> result = new ArrayList<>();
        for(WorkdayDermatologist workday : dermatologist.getWorkdays())
        {
            List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
            for(Examination examination : workday.getExaminations())
                appointmentDTOS.add(new AppointmentDTO(examination));
            result.add(new WorkDayDTO(workday.getId(),workday.getDate(),appointmentDTOS));
        }
        return result;
    }
}
