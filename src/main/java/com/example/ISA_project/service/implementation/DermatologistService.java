package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.AppointmentDTO;
import com.example.ISA_project.model.dto.DermatologistDTO;
import com.example.ISA_project.model.dto.WorkDayDTO;
import com.example.ISA_project.repository.DermatologistRepository;
import com.example.ISA_project.service.IConsultationService;
import com.example.ISA_project.service.IDermatologistService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DermatologistService implements IDermatologistService {

    private final DermatologistRepository dermatologistRepository;

    public DermatologistService(DermatologistRepository dermatologistRepository)
    {
        this.dermatologistRepository = dermatologistRepository;
    }

    public List<Dermatologist> findAll(){return dermatologistRepository.findAll();}

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

    @Override
    public void addVacation(Vacation vacation) {
        Dermatologist dermatologist = dermatologistRepository.getOne(vacation.getUser_id());
        List<Vacation> vacations = dermatologist.getVacation();
        vacations.add(vacation);
        dermatologist.setVacation(vacations);
        dermatologistRepository.save(dermatologist);
    }

    @Override
    public Dermatologist getById(int id) {
        return dermatologistRepository.findOneById(id);
    }


    public List<Period> getPeriods(WorkdayDermatologist workday, WorkingHours workingHours) {
        boolean check = false;
        List<Period> periods = new ArrayList<>();

        LocalDateTime start = LocalDateTime.of(workday.getDate(), workingHours.getStartTime());
        LocalDateTime end = LocalDateTime.of(workday.getDate(), workingHours.getEndTime());
        LocalDateTime end_period = start.plusMinutes(30);
        while (!check) {
            periods.add(new Period(start, start.plusMinutes(30)));
            start = start.plusMinutes(30);
            end_period = end_period.plusMinutes(30);
            check = end.isEqual(start);
        }
        return periods;
    }

    @Override
    public List<Period> freePeriods(int id, LocalDate date, int pharmacyId) {
        Dermatologist dermatologist = dermatologistRepository.findOneById(id);
        WorkdayDermatologist workday = null;
        List<Period> periods = new ArrayList<>();

        for (WorkdayDermatologist workdayDermatologist : dermatologist.getWorkdays()) {
            if (workdayDermatologist.getDate().equals(date)) {
                workday = workdayDermatologist;
                break;
            }
        }

        if (workday == null) {
            return periods;
        }
        WorkingHours workingHours = null;

        for(WorkingHours wh : dermatologist.getWorkingHours())
        {
            if(wh.getPharmacy().getId() == pharmacyId)
            {
                workingHours = wh;
                break;
            }
        }

        if (workingHours == null) {
            return periods;
        }

        periods = getPeriods(workday, workingHours);

        for (Examination examination : workday.getExaminations()) {
            for (Period period : periods) {
                if (examination.getDate().getStart_date().equals(period.getStart_date())) {
                    periods.remove(period);
                    break;
                }
            }
        }
        return periods;
    }

    @Override
    public void addNewExamination(Examination examination) {
        Dermatologist dermatologist = dermatologistRepository.findOneById(examination.getDermatologist().getId());
        for(WorkdayDermatologist workdayDermatologist : dermatologist.getWorkdays())
        {
            if(examination.getDate().getStart_date().toLocalDate().equals(workdayDermatologist.getDate())) {
                workdayDermatologist.getExaminations().add(examination);
                break;
            }
        }
        dermatologistRepository.save(dermatologist);
    }

    @Override
    public List<DermatologistDTO> getAll() {
        List<DermatologistDTO> dermatologistDTOS = new ArrayList<>();
        try{
            List<Dermatologist> dermatologists = dermatologistRepository.findAll();
            for( Dermatologist d : dermatologists){
                dermatologistDTOS.add(new DermatologistDTO(d));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return  dermatologistDTOS;
    }

    @Override
    public List<DermatologistDTO> search(String input) {
        List<DermatologistDTO> dermatologistDTOS = new ArrayList<>();
        try{
            List<Dermatologist> dermatologists = dermatologistRepository.findAll();
            for( Dermatologist d : dermatologists){
                if(d.getUser().getFullName().toLowerCase().contains(input.toLowerCase())) {
                    dermatologistDTOS.add(new DermatologistDTO(d));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return  dermatologistDTOS;
    }

    @Override
    public void save(Dermatologist dermatologist) {
        dermatologistRepository.save(dermatologist);
    }


}
