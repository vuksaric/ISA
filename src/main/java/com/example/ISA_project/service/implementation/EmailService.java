package com.example.ISA_project.service.implementation;

import com.example.ISA_project.config.EmailContext;
import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Examination;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.service.IEmailService;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class EmailService implements IEmailService {

    private final EmailContext emailContext;

    public EmailService(EmailContext emailContext) {
        this.emailContext = emailContext;

    }


    @Override
    public void issueReservationEmail(Patient patient) {

        String to = patient.getUser().getEmail();
        String title = "Your reservation has been confirmed";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", patient.getUser().getName(), patient.getUser().getSurname()));
        emailContext.send(to, title, "issueReservation", context);

    }

    @Override
    public void newExamination(Examination examination) {

        String to = examination.getPatient().getUser().getEmail();
        String title = "New examination";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", examination.getPatient().getUser().getName(), examination.getPatient().getUser().getSurname()));
        context.setVariable("date", String.format("%s %s", examination.getDate().getStart_date(), examination.getDate().getEnd_date()));
        context.setVariable("pharmacy", String.format("%s", examination.getPharmacy().getName()));
        emailContext.send(to, title, "newExamination", context);

    }

    @Override
    public void newConsultation(Consultation consultation) {

        String to = consultation.getPatient().getUser().getEmail();
        String title = "New consultation";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", consultation.getPatient().getUser().getName(), consultation.getPatient().getUser().getSurname()));
        context.setVariable("date", String.format("%s %s", consultation.getPeriod().getStart_date(), consultation.getPeriod().getEnd_date()));
        context.setVariable("pharmacy", String.format("%s", consultation.getPharmacy().getName()));
        emailContext.send(to, title, "newConsultation", context);

    }


}