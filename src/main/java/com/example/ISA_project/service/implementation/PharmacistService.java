package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.AppointmentDTO;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.model.dto.ReservationDTO;
import com.example.ISA_project.model.dto.WorkDayDTO;
import com.example.ISA_project.repository.ConsultationRepository;
import com.example.ISA_project.repository.PharmacistRepository;
import com.example.ISA_project.service.IConsultationService;
import com.example.ISA_project.service.IExaminationService;
import com.example.ISA_project.service.IPharmacistService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacistService implements IPharmacistService {

    private final PharmacistRepository pharmacistRepository;
    private final UserService userService;
    private final ReservationService reservationService;



    public PharmacistService(PharmacistRepository pharmacistRepository, UserService userService, ReservationService reservationService) {
        this.pharmacistRepository = pharmacistRepository;
        this.userService = userService;
        this.reservationService = reservationService;
    }


    public ProfileDTO getProfile(int id) {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        return userService.getProfile(pharmacist.getUser().getId());
    }

    public List<WorkDayDTO> getWorkdays(int id) {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        List<WorkDayDTO> result = new ArrayList<>();
        for (WorkdayPharmacist workday : pharmacist.getWorkdays()) {
            List<AppointmentDTO> consultationDTOS = new ArrayList<>();
            for (Consultation consultation : workday.getConsultations())
                consultationDTOS.add(new AppointmentDTO(consultation));
            result.add(new WorkDayDTO(workday.getId(), workday.getDate(), consultationDTOS));
        }
        return result;
    }

    @Override
    public List<ReservationDTO> getReservations(int id) {

        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        return reservationService.getByPharmacy(pharmacist.getPharmacy().getName());
    }

    public List<Period> getPeriods(WorkdayPharmacist workday, WorkingHours workingHours) {
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
    public List<Period> freePeriods(int id, LocalDate date) {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        WorkdayPharmacist workday = null;
        List<Period> periods = new ArrayList<>();

        for (WorkdayPharmacist workdayPharmacist : pharmacist.getWorkdays()) {
            if (workdayPharmacist.getDate().equals(date)) {
                workday = workdayPharmacist;
                break;
            }
        }

        if (workday == null) {
            return periods;
        }

        periods = getPeriods(workday, pharmacist.getWorkingHours());

        for (Consultation consultation : workday.getConsultations()) {
            for (Period period : periods) {
                if (consultation.getPeriod().getStart_date().equals(period.getStart_date())) {
                    periods.remove(period);
                    break;
                }
            }
        }
        return periods;
    }

    @Override
    public void addNewConsultation(Consultation consultation) {
        Pharmacist pharmacist = pharmacistRepository.findOneById(consultation.getPharmacist().getId());
        for(WorkdayPharmacist workdayPharmacist : pharmacist.getWorkdays())
        {
            if(consultation.getPeriod().getStart_date().toLocalDate().equals(workdayPharmacist.getDate())) {
                workdayPharmacist.getConsultations().add(consultation);
                break;
            }
        }
        pharmacistRepository.save(pharmacist);
    }
}
