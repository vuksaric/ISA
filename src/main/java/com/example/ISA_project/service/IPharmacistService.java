package com.example.ISA_project.service;

import com.example.ISA_project.model.Pharmacist;
import com.example.ISA_project.model.dto.*;

import java.util.List;


public interface IPharmacistService {

        ProfileDTO getProfile(int id);
        List<WorkDayDTO> getWorkdays(int id);
        List<ReservationDTO> getReservations(int id);
        void newPharmacist(RegistrationDTO registrationDTO);
        List<PharmacistDTO> getAll();
        List<PharmacistDTO> search(String input);

}
