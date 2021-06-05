package com.example.ISA_project.service;


import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Examination;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.Reservation;

import com.example.ISA_project.model.dto.PatientSearchDTO;

import com.example.ISA_project.model.dto.PenaltyDTO;

import com.example.ISA_project.model.dto.ProfileDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IPatientService {
    ProfileDTO getPatientInfo(int id);
    List<PatientSearchDTO> getPatientNames();
    void saveConsultation(Consultation consultation);
    void saveExamination(Examination examination);
    Patient findOneById(int id);
    void addReservation(int id, Reservation reservation);
    void saveFutureConsultation(Consultation consultation);
    void saveFutureExamination(Examination examination);
    List<PenaltyDTO> getPatientPenaltyPoints(int id);
    void addPenaltyPoint(int id);
    void removeFutureConsultation(Consultation consultation);
    void removeFutureExamination(Examination examination);
    void cancelConsultation(Consultation consultation);
    Boolean save(Patient patient);
    Patient getByUserId(int user_id);
}

