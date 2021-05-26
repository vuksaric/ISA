package com.example.ISA_project.service;

import com.example.ISA_project.model.dto.ProfileDTO;

public interface IPatientService {
    ProfileDTO getPatientInfo(int id);
}
