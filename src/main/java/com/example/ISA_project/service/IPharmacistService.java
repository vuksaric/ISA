package com.example.ISA_project.service;

import com.example.ISA_project.model.Pharmacist;
import com.example.ISA_project.model.Vacation;
import com.example.ISA_project.model.dto.*;
import com.example.ISA_project.model.Period;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.model.dto.ReservationDTO;
import com.example.ISA_project.model.dto.WorkDayDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface IPharmacistService {
        ProfileDTO getProfile(int id);
        List<WorkDayDTO> getWorkdays(int id);
        List<ReservationDTO> getReservations(int id);
        void newPharmacist(RegistrationDTO registrationDTO);
        List<PharmacistDTO> getAll();
        List<PharmacistDTO> search(String input);
        List<Period> freePeriods(int id, LocalDate date);
        void addVacation(Vacation vacation);
        Pharmacist getById(int id);
        void delete(int id);
}
