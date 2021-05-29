package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.*;
import com.example.ISA_project.model.dto.AppointmentDTO;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.model.dto.ReservationDTO;
import com.example.ISA_project.model.dto.WorkDayDTO;
import com.example.ISA_project.repository.ConsultationRepository;
import com.example.ISA_project.repository.PharmacistRepository;
import com.example.ISA_project.service.IPharmacistService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacistService implements IPharmacistService {

    private final PharmacistRepository pharmacistRepository;
    private final ConsultationRepository consultationRepository;
    private final UserService userService;
    private final ReservationService reservationService;


    public PharmacistService(PharmacistRepository pharmacistRepository, ConsultationRepository consultationRepository, UserService userService, ReservationService reservationService) {
        this.pharmacistRepository = pharmacistRepository;
        this.consultationRepository = consultationRepository;
        this.userService = userService;
        this.reservationService = reservationService;
    }


    public ProfileDTO getProfile(int id)
    {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        return userService.getProfile(pharmacist.getUser().getId());
    }

    public List<WorkDayDTO> getWorkdays(int id)
    {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        List<WorkDayDTO> result = new ArrayList<>();
        for(WorkdayPharmacist workday : pharmacist.getWorkdays())
        {
            List<AppointmentDTO> consultationDTOS = new ArrayList<>();
            for(Consultation consultation : workday.getConsultations())
                consultationDTOS.add(new AppointmentDTO(consultation));
            result.add(new WorkDayDTO(workday.getId(),workday.getPeriod().getStart_date(),consultationDTOS));
        }
        return result;
    }

    @Override
    public List<ReservationDTO> getReservations(int id) {

        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        return reservationService.getByPharmacy(pharmacist.getPharmacy().getName());
    }

    public List<Period> getPeriods(WorkdayPharmacist workday)
    {
        boolean check = false;
        List<Period> periods = new ArrayList<>();
        LocalDateTime start = workday.getPeriod().getStart_date();
        LocalDateTime end = workday.getPeriod().getEnd_date();
        while(!check)
        {
            periods.add(new Period(start,start.plusMinutes(30)));
            start.plusMinutes(30);
            check = end.isEqual(start);
        }
        return periods;
    }

    @Override
    public List<Period> freePeriods(int id, LocalDate date) {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        WorkdayPharmacist workday = null;

        for(WorkdayPharmacist workdayPharmacist : pharmacist.getWorkdays())
        {
            if(workdayPharmacist.getPeriod().getStart_date().toLocalDate().equals(date))
                workday = workdayPharmacist;
        }

        List<Period> periods = getPeriods(workday);

        for(Consultation consultation : workday.getConsultations())
        {
            for(Period period : periods)
            {
                if(consultation.getPeriod().getStart_date().equals(period.getStart_date())) {
                    periods.remove(period);
                    break;
                }
            }
        }
        return periods;
    }

}
