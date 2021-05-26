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
        return userService.getProfile(id);
    }

    public List<WorkDayDTO> getWorkdays(int id)
    {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        List<WorkDayDTO> result = new ArrayList<>();
        for(WorkdayPharmacist workday : pharmacist.getWorkdays())
        {
            List<AppointmentDTO> consultationDTOS = new ArrayList<>();
            for(Consultation consultation : workday.getConsultations())
                consultationDTOS.add(new AppointmentDTO(consultation.getId(),consultation.getPeriod().getStart_date(),consultation.getPeriod().getEnd_date(),consultation.getPharmacy().getName(),consultation.getPatient().getUser().getFullName()));
            result.add(new WorkDayDTO(workday.getId(),workday.getPeriod().getStart_date(),consultationDTOS));
        }
        return result;
    }

    @Override
    public List<ReservationDTO> getReservations(int id) {

        Pharmacist pharmacist = pharmacistRepository.findOneById(id);
        return reservationService.getByPharmacy(pharmacist.getPharmacy().getName());
    }

}
