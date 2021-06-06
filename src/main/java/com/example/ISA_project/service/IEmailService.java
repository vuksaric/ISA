package com.example.ISA_project.service;

import com.example.ISA_project.model.OrderList;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.User;
import com.example.ISA_project.model.dto.ActionDTO;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.model.dto.VacationDTO;

public interface IEmailService {

    void issueReservationEmail(Patient patient);
    void rejectVacationRequest(User user, VacationDTO vacationDTO);
    void sendPromotions(Patient patient, ActionDTO actionDTO);
    void rejectOffer(ProfileDTO profileDTO, OrderList orderList);
    void acceptOffer(ProfileDTO profileDTO, OrderList orderList);
}
