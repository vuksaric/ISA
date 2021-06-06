package com.example.ISA_project.service.implementation;

import com.example.ISA_project.config.EmailContext;
import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Examination;
import com.example.ISA_project.model.OrderList;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.User;
import com.example.ISA_project.model.dto.ActionDTO;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.model.dto.VacationDTO;
import com.example.ISA_project.model.Reservation;
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


    public void scheduleExaminationEmail(Examination examination) {
        String to = examination.getPatient().getUser().getEmail();
        String title = "Scheduled Examination";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", examination.getPatient().getUser().getName(), examination.getPatient().getUser().getSurname()));
        context.setVariable("pharmacy", String.format("%s", examination.getPharmacy().getName()));
        context.setVariable("date", String.format("%s", examination.getDate().getStart_date().toLocalDate().toString()));
        context.setVariable("time", String.format("%s", examination.getDate().getStart_date().toLocalTime().toString()+" - "+examination.getDate().getEnd_date().toLocalTime().toString()));
        emailContext.send(to, title, "scheduleExamination", context);
    }

    @Override
    public void scheduleConsultationEmail(Consultation consultation) {
        String to = consultation.getPatient().getUser().getEmail();
        String title = "Scheduled Consultation";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", consultation.getPatient().getUser().getName(), consultation.getPatient().getUser().getSurname()));
        context.setVariable("pharmacy", String.format("%s", consultation.getPharmacy().getName()));
        context.setVariable("date", String.format("%s", consultation.getPeriod().getStart_date().toLocalDate().toString()));
        context.setVariable("time", String.format("%s", consultation.getPeriod().getStart_date().toLocalTime().toString()+" - "+consultation.getPeriod().getEnd_date().toLocalTime().toString()));
        emailContext.send(to, title, "scheduleExamination", context);
    }

    @Override
    public void makeReservationEmail(Reservation reservation) {
        String to = reservation.getPatient().getUser().getEmail();
        String title = "Medicine Reservation";
        Context context = new Context();

        context.setVariable("name", String.format("%s %s", reservation.getPatient().getUser().getName(), reservation.getPatient().getUser().getSurname()));
        context.setVariable("pharmacy", String.format("%s", reservation.getPharmacy().getName()));
        context.setVariable("date", String.format("%s", reservation.getDueDate().toLocalDate().toString()));
        context.setVariable("number", String.format("%s", reservation.getSerialNumber()));
        context.setVariable("medicine", String.format("%s", reservation.getMedicine().getMedicineInformation()));
        emailContext.send(to, title, "makeReservation", context);
    }

    public void sendPromotions(Patient patient, ActionDTO actionDTO) {
        String to = patient.getUser().getEmail();
        String title = "New promotion";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", patient.getUser().getName(), patient.getUser().getSurname()));
        context.setVariable("promotion", String.format("%s", actionDTO.getText()));
        context.setVariable("duration", String.format("%s", "from " + actionDTO.getStartDate().toString() + " to" + actionDTO.getEndDate().toString()));
        emailContext.send(to, title, "Promotion", context);
    }

    @Override
    public void rejectOffer(ProfileDTO profileDTO, OrderList orderList) {
        String to = profileDTO.getEmail();
        String title = "Offer rejection";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", profileDTO.getName(), profileDTO.getSurname()));
        context.setVariable("object", String.format("%s for quantity  %s", orderList.getMedicine().getMedicine().getName(), orderList.getMedicine().getQuantity()));
        emailContext.send(to, title, "OfferRejection", context);
    }

    @Override
    public void acceptOffer(ProfileDTO profileDTO, OrderList orderList) {
        String to = profileDTO.getEmail();
        String title = "Offer acceptance";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", profileDTO.getName(), profileDTO.getSurname()));
        context.setVariable("object", String.format("%s for quantity  %s", orderList.getMedicine().getMedicine().getName(), orderList.getMedicine().getQuantity()));
        emailContext.send(to, title, "OfferAcceptance", context);
    }


}