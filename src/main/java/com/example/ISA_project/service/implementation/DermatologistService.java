package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.AppointmentDTO;
import com.example.ISA_project.model.dto.CheckVacationRequest;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.model.dto.WorkDayDTO;
import com.example.ISA_project.repository.DermatologistRepository;
import com.example.ISA_project.service.IConsultationService;
import com.example.ISA_project.service.IDermatologistService;
import com.example.ISA_project.service.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DermatologistService implements IDermatologistService {

    private final DermatologistRepository dermatologistRepository;
    private final IUserService userService;

    public DermatologistService(DermatologistRepository dermatologistRepository, IUserService userService)
    {
        this.dermatologistRepository = dermatologistRepository;
        this.userService = userService;
    }

    @Override
    public ProfileDTO getProfile(int id) {
        Dermatologist dermatologist = dermatologistRepository.findOneById(id);
        return userService.getProfile(dermatologist.getUser().getId());
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
    public boolean checkVacation(CheckVacationRequest request) {
        Dermatologist dermatologist = dermatologistRepository.findOneById(request.getId());
        LocalDate date = request.getDate();
        for (Vacation vacation : dermatologist.getVacation()) {
            if (date.equals(vacation.getStart_date().toLocalDate()) && request.getPharmacyId() == vacation.getPharmacy_id())
                return false;

            if (date.equals(vacation.getEnd_date().toLocalDate()) && request.getPharmacyId() == vacation.getPharmacy_id())
                return false;

            if (date.isAfter(vacation.getStart_date().toLocalDate()) && date.isBefore(vacation.getEnd_date().toLocalDate()) && request.getPharmacyId() == vacation.getPharmacy_id())
                return false;
        }
        return true;
    }

    public Dermatologist getByUserId(int user_id) {
        return dermatologistRepository.findOneByUserId(user_id);
    }
}
