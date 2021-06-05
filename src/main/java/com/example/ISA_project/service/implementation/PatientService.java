package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Examination;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.PatientChart;
import com.example.ISA_project.model.Reservation;

import com.example.ISA_project.model.dto.PatientSearchDTO;

import com.example.ISA_project.model.dto.PenaltyDTO;

import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.repository.PatientRepository;
import com.example.ISA_project.service.IPatientService;
import com.example.ISA_project.service.IUserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService implements IPatientService {

    private final PatientRepository patientRepository;
    private final IUserService userService;

    public PatientService(PatientRepository patientRepository, IUserService userService){

        this.patientRepository=patientRepository;
        this.userService = userService;
    }

    public ProfileDTO getPatientInfo(int id){
        Patient patient = patientRepository.findOneById(id);
        int idUser = patient.getUser().getId();
        ProfileDTO profileDTO=userService.getProfile(idUser);
        profileDTO.setType(patient.getPatientCategory().toString());
        return profileDTO;
    }

    @Override
    public List<PatientSearchDTO> getPatientNames() {

        List<Patient> patients = patientRepository.findAll();
        List<PatientSearchDTO> names = new ArrayList<>();
        for (Patient patient : patients)
            names.add(new PatientSearchDTO(patient.getUser().getFullName(),patient.getId()));

        return names;
    }

    @Override
    public void saveConsultation(Consultation consultation) {
        Patient patient = patientRepository.findOneById(consultation.getPatient().getId());
        patient.getPatientChart().getPreviousConsultations().add(consultation);
        patientRepository.save(patient);
    }

    @Override
    public void saveExamination(Examination examination) {
        Patient patient = patientRepository.findOneById(examination.getPatient().getId());
        patient.getPatientChart().getPreviousExaminations().add(examination);
    }

    public Patient findOneById(int id) {
        return patientRepository.findOneById(id);
    }

    @Override
    public void addReservation(int id, Reservation reservation){
        Patient patient = patientRepository.findOneById(id);
        PatientChart patientChart = patient.getPatientChart();
        patientChart.getReservations().add(reservation);
        patient.setPatientChart(patientChart);
        patientRepository.save(patient);
    }

    @Override
    public void saveFutureConsultation(Consultation consultation) {
        Patient patient = patientRepository.findOneById(consultation.getPatient().getId());
        patient.getPatientChart().getFutureConsultations().add(consultation);
        patientRepository.save(patient);
    }

    @Override
    public void cancelConsultation(Consultation consultation){
        Patient patient = patientRepository.findOneById(consultation.getPatient().getId());
        patient.getPatientChart().getFutureConsultations().remove(consultation);
        patientRepository.save(patient);
    }

    @Override
    public void saveFutureExamination(Examination examination) {
        Patient patient = patientRepository.findOneById(examination.getPatient().getId());
        patient.getPatientChart().getFutureExaminations().add(examination);
        patientRepository.save(patient);
    }

    @Override
    public List<PenaltyDTO> getPatientPenaltyPoints(int id) {
        List<PenaltyDTO> penaltyDTOS = new ArrayList<>();
        List<LocalDate> penaltyPoints = new ArrayList<>();
        Patient patient = patientRepository.findOneById(id);
        penaltyPoints.addAll(deletePatientPoints(patient));
        penaltyPoints.addAll(penaltyReservations(patient));

        patient.setPenaltyPoints(penaltyPoints);
        patientRepository.save(patient);

        for(LocalDate date : patient.getPenaltyPoints()){
            penaltyDTOS.add(new PenaltyDTO(date, 1));
        }
        return penaltyDTOS;
    }

    @Override
    public Boolean save(Patient patient) {
        if(patientRepository.save(patient) != null)
            return true;
        else return false;
    }

    @Override
    public Patient getByUserId(int user_id) {
        return patientRepository.findOneByUserId(user_id);
    }

    private List<LocalDate> deletePatientPoints(Patient patient) {
       List<LocalDate> penaltyPoints = new ArrayList<>();
       if(LocalDate.now().getDayOfMonth()>=1){
           for(LocalDate date : patient.getPenaltyPoints()){
               if(date.getMonth().equals(LocalDate.now().getMonth())){
                   penaltyPoints.add(date);
               }
           }
       }
        patient.setPenaltyPoints(penaltyPoints);
        patientRepository.save(patient);
       return patient.getPenaltyPoints();
    }


    private List<LocalDate> penaltyReservations(Patient patient){
        List<LocalDate> penaltyPoints = new ArrayList<>();
        for(Reservation reservation : patient.getPatientChart().getReservations()){
            LocalDate dueDate = reservation.getDueDate().toLocalDate();
            if(dueDate.isBefore(LocalDate.now()) && !reservation.isIssued() &&!reservation.isCanceled() && !reservation.isPenalty()){
                penaltyPoints.add(dueDate);
                int i = patient.getPatientChart().getReservations().indexOf(reservation);
                patient.getPatientChart().getReservations().get(i).setPenalty(true);
            }
        }
        patient.setPenaltyPoints(penaltyPoints);
        patientRepository.save(patient);
        return patient.getPenaltyPoints();
    }
}
