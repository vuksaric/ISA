package com.example.ISA_project.service.implementation;

import com.example.ISA_project.config.EmailContext;
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
}