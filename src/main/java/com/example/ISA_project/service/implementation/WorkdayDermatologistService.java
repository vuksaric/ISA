package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Examination;
import com.example.ISA_project.model.WorkdayDermatologist;
import com.example.ISA_project.model.dto.AppointmentDTO;
import com.example.ISA_project.repository.WorkdayDermatologistRepository;
import com.example.ISA_project.service.IWorkdayDermatologistService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkdayDermatologistService implements IWorkdayDermatologistService {

    private final WorkdayDermatologistRepository workdayDermatologistRepository;

    public WorkdayDermatologistService(WorkdayDermatologistRepository workdayDermatologistRepository)
    {
        this.workdayDermatologistRepository = workdayDermatologistRepository;
    }

    @Override
    public List<AppointmentDTO> getExaminations(int id) {
        WorkdayDermatologist workday = workdayDermatologistRepository.findOneById(id);
        List<AppointmentDTO> result = new ArrayList<>();

        for(Examination examination : workday.getExaminations())
        {
            result.add(new AppointmentDTO(examination));
        }
        return result;
    }
}
