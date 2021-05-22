package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.repository.PatientRepository;
import com.example.ISA_project.service.IPatientService;
import org.springframework.stereotype.Service;

@Service
public class PatientService implements IPatientService {

    private final PatientRepository patientRepository;
    private final UserService userService;

    public PatientService(PatientRepository patientRepository, UserService userService){

        this.patientRepository=patientRepository;
        this.userService = userService;
    }

    public ProfileDTO getPatientInfo(int id){
        return userService.getProfile(id);
    }


}
