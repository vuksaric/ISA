package com.example.ISA_project.service;


import com.example.ISA_project.model.Consultation;
import com.example.ISA_project.model.Examination;
import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.Reservation;
import com.example.ISA_project.model.dto.ProfileDTO;

import java.util.List;

public interface IPatientService {
    ProfileDTO getPatientInfo(int id);
    List<String> getPatientNames();
    void saveConsultation(Consultation consultation);
    void saveExamination(Examination examination);
    Patient findOneById(int id);
    void addReservation(int id, Reservation reservation);
}
