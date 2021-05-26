package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.repository.PatientRepository;
import com.example.ISA_project.service.IPatientService;
import com.example.ISA_project.service.IUserService;
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
        return userService.getProfile(id);
    }

    @Override
    public List<String> getPatientNames() {

        List<Patient> patients = patientRepository.findAll();
        List<String> names = new ArrayList<>();
        for (Patient patient : patients)
            names.add(patient.getUser().getFullName());

        return names;
    }


}
