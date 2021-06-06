package com.example.ISA_project.service;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Examination;
import com.example.ISA_project.model.OrderList;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.User;
import com.example.ISA_project.model.dto.ActionDTO;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.model.dto.VacationDTO;
import com.example.ISA_project.model.Reservation;

public interface IEmailService {

    void issueReservationEmail(Patient patient);
    void newExamination(Examination examination);
    void newConsultation(Consultation consultation);
    void rejectVacationRequest(User user, VacationDTO vacationDTO);
    void scheduleExaminationEmail(Examination examination);
    void scheduleConsultationEmail(Consultation consultation);
    void makeReservationEmail(Reservation reservation);
    void sendPromotions(Patient patient, ActionDTO actionDTO);
    void rejectOffer(ProfileDTO profileDTO, OrderList orderList);
    void acceptOffer(ProfileDTO profileDTO, OrderList orderList);
}
