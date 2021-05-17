package com.example.ISA_project.service.implementation;

import com.example.ISA_project.model.Patient;
import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.repository.PatientRepository;
import com.example.ISA_project.service.IPatientService;
import org.springframework.stereotype.Service;

@Service
public class PatientService implements IPatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){

        this.patientRepository=patientRepository;
    }

    public ProfileDTO getPatientInfo(int id){
        Patient patient = patientRepository.findOneById(id);
        ProfileDTO profile = new ProfileDTO(patient.getUser().getUsername(),patient.getUser().getName(), patient.getUser().getSurname(),
                patient.getUser().getEmail(), patient.getUser().getAddress(), patient.getUser().getState(),
                patient.getUser().getTown(), patient.getUser().getPhone());
        return profile;
    }


}
