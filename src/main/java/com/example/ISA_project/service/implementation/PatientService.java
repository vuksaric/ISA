package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Examination;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.PatientChart;
import com.example.ISA_project.model.Reservation;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.repository.PatientRepository;
import com.example.ISA_project.service.IPatientService;
import com.example.ISA_project.service.IUserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

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
        int idUser = patientRepository.findOneById(id).getUser().getId();
        return userService.getProfile(idUser);
    }

    @Override
    public List<String> getPatientNames() {

        List<Patient> patients = patientRepository.findAll();
        List<String> names = new ArrayList<>();
        for (Patient patient : patients)
            names.add(patient.getUser().getFullName());

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


}
