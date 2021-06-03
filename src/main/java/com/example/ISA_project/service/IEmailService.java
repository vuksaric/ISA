package com.example.ISA_project.service;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Examination;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.Reservation;

public interface IEmailService {

    void issueReservationEmail(Patient patient);
    void scheduleExaminationEmail(Examination examination);
    void scheduleConsultationEmail(Consultation consultation);
    void makeReservationEmail(Reservation reservation);

}
