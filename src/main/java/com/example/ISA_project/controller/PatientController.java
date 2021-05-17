package com.example.ISA_project.controller;

import com.example.ISA_project.model.dto.ProfileDTO;
import com.example.ISA_project.service.IPatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final IPatientService patientService;

    public PatientController(IPatientService patientService){
        this.patientService=patientService;
    }
    @GetMapping(value="/{id}")
    public ProfileDTO getPatientInfo(@PathVariable String id){
        Integer profileId = Integer.parseInt(id);
        return patientService.getPatientInfo(profileId);
    }
}
