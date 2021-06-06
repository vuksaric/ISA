package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.WorkingHours;
import com.example.ISA_project.repository.WorkingHoursRepository;
import com.example.ISA_project.service.IWorkingHoursService;
import org.springframework.stereotype.Service;

@Service
public class WorkingHoursService implements IWorkingHoursService {

    private final WorkingHoursRepository workingHoursRepository;

    public WorkingHoursService(WorkingHoursRepository workingHoursRepository) {
        this.workingHoursRepository = workingHoursRepository;
    }

    @Override
    public WorkingHours saveWorkingHours(WorkingHours workingHours) {
        return workingHoursRepository.save(workingHours);
    }
}
