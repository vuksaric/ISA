package com.example.ISA_project.service.implementation;

import com.example.ISA_project.config.EmailContext;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.User;
import com.example.ISA_project.model.dto.VacationDTO;
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
    public void rejectVacationRequest(User user, VacationDTO vacationDTO) {
        String to = user.getEmail();
        String title = "Your request has been rejected";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", user.getName(),user.getSurname()));
        context.setVariable("reason", String.format("%s", vacationDTO.getRejectionNote()));
        context.setVariable("period", String.format("%s - %s", vacationDTO.getStart_date().toString(), vacationDTO.getEnd_date().toString()));
        emailContext.send(to, title, "Vacation", context);
    }
}