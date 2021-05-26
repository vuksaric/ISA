package com.example.ISA_project.service;

import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.dto.ProfileDTO;

import java.util.List;

public interface IPatientService {
    ProfileDTO getPatientInfo(int id);
    List<String> getPatientNames();
    Patient findOneById(int id);
}
