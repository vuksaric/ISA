package com.example.ISA_project.service;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Examination;
import com.example.ISA_project.model.Patient;

public interface IEmailService {

    void issueReservationEmail(Patient patient);
    void newExamination(Examination examination);
    void newConsultation(Consultation consultation);
}
